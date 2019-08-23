package com.myroad2pro.beepos.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.myroad2pro.beepos.domain.Account;
import com.myroad2pro.beepos.domain.Profile;
import com.myroad2pro.beepos.service.AccountService;
import com.myroad2pro.beepos.service.ProfileService;

@Controller
public class MainController {
	@Autowired
	AccountService accountService;
	
	@Autowired
	ProfileService profileService;
	
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
}
