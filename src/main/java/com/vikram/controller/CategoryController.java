package com.vikram.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vikram.entity.Category;
import com.vikram.service.CategoryService;

@Controller
@RequestMapping("/admin/category")
public class CategoryController {
	
	@Autowired
	CategoryService categoryService;
	
	@GetMapping({"/list"})
	public String listAllCategories(Model model) {
		
		model.addAttribute("categories", categoryService.findAll());
		return "list-categories";
	}
	
	
	
	@GetMapping("/add")
	public String showCategoryForm(Model model) {
		model.addAttribute("category", new Category());
		return "category-form";
	}
	
	@GetMapping("/edit")
	public String showCategoryEditForm(Model model,@RequestParam int id) {
		model.addAttribute("category",categoryService.findById(id));
		return "category-form";
	}
	
	@PostMapping("/add")
	public String addCategory(@ModelAttribute("category") Category category) {
		categoryService.saveOrUpdate(category);
		return "redirect:/category/list";
	}
	
	
	@GetMapping("/delete")
	public String deleteCategory(@RequestParam int id) {
		categoryService.removeById(id);
		return "redirect:/category/list";
	}
}
