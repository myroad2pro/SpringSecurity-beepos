package com.myroad2pro.beepos.controller;

import java.security.Principal;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myroad2pro.beepos.domain.Account;
import com.myroad2pro.beepos.domain.LabelValue;
import com.myroad2pro.beepos.domain.Profile;
import com.myroad2pro.beepos.domain.Role;
import com.myroad2pro.beepos.service.AccountService;
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

	@GetMapping("/")
	public String home() {
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

	@GetMapping("/profile")
	public String profile(Model model, Principal principal) {
		String principalName = principal.getName();
		Account account = accountService.findOne(principalName);
		Profile profile = profileService.findOne(account.getId());
		model.addAttribute("profile", profile);
		return "profile";
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
}
