package com.example.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

	@GetMapping("/")
	public String index() {
		return "index";
	}
	
//	@GetMapping("/profile")
//	@PreAuthorize("hasAuthority('ROLE_USER')")
//	public String home() {
//		return "profile";
//	}
//	
//	@GetMapping("/about")
//	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
//	public String about() {
//		return "about";
//	}
	
	@GetMapping("/about")
	public String about() {
		return "about";
	}
	
	@GetMapping("/home")
	public String home() {
		return "home";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/user-logout")
	public String logout() {
		return "logout";
	}
	
}
