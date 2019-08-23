package com.myroad2pro.beepos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

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
	public String profile() {
		return "profile";
	}

	
	@GetMapping("/403")
	public String accessDenied() {
		return "403";
	}
}
