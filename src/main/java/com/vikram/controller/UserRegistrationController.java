package com.vikram.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.vikram.dto.UserRegistrationDto;
import com.vikram.service.UserService;

@Controller
@RequestMapping("/registration")
public class UserRegistrationController {
	
	private UserService userService;

	public UserRegistrationController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@GetMapping
	public String showRegostrationForm() {
		
		return "registration";
	}
	
	@ModelAttribute("user")
	public UserRegistrationDto userRegistrationDto() {
		
		return new UserRegistrationDto();
	}
	
	@PostMapping
	public String registerUser(@ModelAttribute("user") UserRegistrationDto user, Model model) {
		
		userService.save(user);
		return "redirect:/registration?success";
	}

}
