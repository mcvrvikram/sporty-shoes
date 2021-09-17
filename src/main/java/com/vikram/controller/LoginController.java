package com.vikram.controller;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.vikram.entity.CustomRole;
import com.vikram.entity.Role;
import com.vikram.security.MyUserDetails;
import com.vikram.service.CategoryService;
import com.vikram.service.ProductService;

@Controller
public class LoginController {
	@Autowired
	CategoryService categoryService;
	@Autowired
	ProductService productService;
	
	@GetMapping({"/login"})
	public String showLogin() {
		return "login";
	}
	
	
	@GetMapping("/")
	public String homePage(@AuthenticationPrincipal MyUserDetails userDetails, Model model ) {
		if(userDetails!=null) {
			Set<Role> authorities = userDetails.getUser().getRole();
			for(Role role:authorities) {
				if(role.getName().equals(CustomRole.ROLE_ADMIN))
					return "redirect:/admin/dashboard";
			}
		}
//		List<Product> products = productService.findAll();
//		model.addAttribute("allProducts",products);
//		model.addAttribute("categories", categoryService.findAll());
		return "redirect:/default/list";			
	}

}
