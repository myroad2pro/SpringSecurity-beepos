package com.myroad2pro.beepos.controller;

import java.security.Principal;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myroad2pro.beepos.domain.Account;
import com.myroad2pro.beepos.domain.Gift;
import com.myroad2pro.beepos.domain.GiftInfo;
import com.myroad2pro.beepos.domain.Hashtag;
import com.myroad2pro.beepos.domain.LabelValue;
import com.myroad2pro.beepos.domain.Profile;
import com.myroad2pro.beepos.domain.Role;
import com.myroad2pro.beepos.repository.AccountRepository;
import com.myroad2pro.beepos.service.AccountService;
import com.myroad2pro.beepos.service.GiftService;
import com.myroad2pro.beepos.service.HashtagService;
import com.myroad2pro.beepos.service.ProfileService;
import com.myroad2pro.beepos.service.RoleService;

@Controller
public class MainController {
	@Autowired
	AccountService accountService;

	@Autowired
	ProfileService profileService;

	@Autowired
	RoleService roleService;

	@Autowired
	HashtagService hashtagService;

	@Autowired
	GiftService giftService;

	@GetMapping("/")
	public String home(Model model, Principal principal) {
		String principalName = principal.getName();
		Account account = accountService.findOne(principalName);
		
		model.addAttribute("hashtagList", hashtagService.findAll());
		List<GiftInfo> giftInfoList = new ArrayList<GiftInfo>();
		Iterable<Gift> giftList = giftService.findAllOrderBySenttimeDesc();
		for (Gift gift : giftList) {
			GiftInfo giftInfo = new GiftInfo(gift.getSender().getProfile().getName(),
					gift.getReceiver().getProfile().getName(), gift.getPoint(), gift.getMessage(),
					gift.getHashtag().getTag());
			giftInfoList.add(giftInfo);
		}
		model.addAttribute("giftInfoList", giftInfoList);

		giftSummary(model, account);
		return "home";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/admin")
	public String admin() {
		return "admin";
	}

	public void giftSummary(Model model, Account account) {
		// get sent gifts information
		List<Gift> sentGifts = giftService.findSentGifts(account.getId());
		model.addAttribute("sentGiftsAmount", sentGifts.size());
		Integer totalSentPoints = giftService.totalSentPoints(account.getId());
		model.addAttribute("totalSentPoints", totalSentPoints);

		// get received gifts information
		List<Gift> receivedGifts = giftService.findReceivedGifts(account.getId());
		model.addAttribute("receivedGiftsAmount", receivedGifts.size());
		Integer totalReceivedPoints = giftService.totalReceivedPoints(account.getId());
		model.addAttribute("totalReceivedPoints", totalReceivedPoints);
	}

	@GetMapping("/profile")
	public String profile(Model model, Principal principal) {
		// get user profile
		String principalName = principal.getName();
		model.addAttribute("email", principalName);
		Account account = accountService.findOne(principalName);
		Profile profile = profileService.findOne(account.getId());
		model.addAttribute("profile", profile);

		giftSummary(model, account);

		// get user's gifts
		List<Gift> giftList = giftService.findUserGifts(account.getId());
		List<GiftInfo> giftInfoList = new ArrayList<GiftInfo>();
		for (Gift gift : giftList) {
			GiftInfo giftInfo = new GiftInfo(gift.getSender().getProfile().getName(),
					gift.getReceiver().getProfile().getName(), gift.getPoint(), gift.getMessage(),
					gift.getHashtag().getTag());
			giftInfoList.add(giftInfo);
		}
		model.addAttribute("giftInfoList", giftInfoList);
		return "profile";
	}

	@GetMapping("/userGifts")
	public String getUserGifts(@RequestParam("option") String option, Principal principal, Model model) {
		String principalName = principal.getName();
		Account account = accountService.findOne(principalName);
		List<Gift> giftList = new ArrayList<Gift>();
		List<GiftInfo> giftInfoList = new ArrayList<GiftInfo>();
		switch (option) {
		case "sent":
			giftList = giftService.findSentGifts(account.getId());
			break;
		case "received":
			giftList = giftService.findReceivedGifts(account.getId());
			break;
		case "all":
			giftList = giftService.findUserGifts(account.getId());
			break;
		default:
			break;
		}
		for (Gift gift : giftList) {
			GiftInfo giftInfo = new GiftInfo(gift.getSender().getProfile().getName(),
					gift.getReceiver().getProfile().getName(), gift.getPoint(), gift.getMessage(),
					gift.getHashtag().getTag());
			giftInfoList.add(giftInfo);
		}
		model.addAttribute("giftInfoList", giftInfoList);
		return "gift :: giftListFragment";
	}

	@GetMapping("/403")
	public String accessDenied() {
		return "403";
	}

	@RequestMapping("/receiverAutocomplete")
	@ResponseBody
	public List<LabelValue> receiverAutocomplete(
			@RequestParam(value = "term", required = false, defaultValue = "") String term) {
		List<LabelValue> suggestions = new ArrayList<LabelValue>();
		List<Profile> profiles = new ArrayList<Profile>();
		try {
			if (term.length() >= 3) {
				profiles = profileService.search(term);
				for (Profile profile : profiles) {
					Account account = profile.getAccount();
					Set<Role> roles = account.getRoles();
					if (roles.contains(roleService.findOne("ROLE_ADMIN")) == false) {
						LabelValue suggestion = new LabelValue();
						suggestion.setLabel(profile.getName() + " " + account.getEmail());
						suggestion.setValue(Integer.toString(account.getId()));
						suggestions.add(suggestion);
					}
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return suggestions;
	}

	@PostMapping("/sendGift")
	public void sendGift(@RequestParam("receiverId") Integer receiverId, @RequestParam("message") String message,
			@RequestParam("points") Integer points, @RequestParam("hashTag") String hashTag, Principal principal) {
		String principalName = principal.getName();
		Account principalAccount = accountService.findOne(principalName);
		Integer senderId = principalAccount.getId();
		if (giftService.createAndSave(senderId, receiverId, hashTag, points, message) != null) {

		}
	}
	
	@PostMapping("/editProfile")
	public void editProfile(@RequestParam("introduction") String introduction, 
			@RequestParam("hobby") String hobby, 
			Principal principal) {
		Account principalAccount = accountService.findOne(principal.getName());
		Profile principalProfile = profileService.findOne(principalAccount.getId());
		profileService.updateProfileIntroduction(principalProfile.getId(), introduction);
		profileService.updateProfileHobby(principalProfile.getId(), hobby);
	}
	
	@GetMapping("/getProfile")
	public String getProfile(Model model, Principal principal) {
		Account account = accountService.findOne(principal.getName());
		Profile profile = profileService.findOne(account.getId());
		model.addAttribute("profile", profile);		
		return "layout :: profileFragment";
	}
}
