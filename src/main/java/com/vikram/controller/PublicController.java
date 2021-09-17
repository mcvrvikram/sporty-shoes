package com.vikram.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vikram.entity.CartQuantity;
import com.vikram.entity.Product;
import com.vikram.service.CategoryService;
import com.vikram.service.ProductService;
import com.vikram.service.SizeService;

@Controller
@RequestMapping("/default")
public class PublicController {

	@Autowired
	ProductService productService;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	SizeService sizeService;
	
	@GetMapping("/list")
	public String listAllProducts(Model model) {
		List<Product> products = productService.findAll();
		model.addAttribute("allProducts",products);
		model.addAttribute("categories", categoryService.findAll());
		return "list-products";
	}
	
	@GetMapping("/search")
	public String listBySearch(Model model, @RequestParam String name ) {
		model.addAttribute("categories", categoryService.findAll());
		model.addAttribute("allProducts",productService.findByNameContainingIgnoreCase(name));
		return "list-products";
	}
	
	@GetMapping("/view")
	public String viewProduct(Model model, @RequestParam int id) {
		
		model.addAttribute("cartQuantity", new CartQuantity());
		model.addAttribute("product",productService.findById(id));
		return "product-details";
	}
	
	@GetMapping("/findByCategory")
	public String findByCategory(Model model, @RequestParam int id) {
		model.addAttribute("categories", categoryService.findAll());
		model.addAttribute("allProducts", productService.findByCategory(categoryService.findById(id)));
		return "list-products";
	}
	
	@GetMapping("/size")
	public String findByProductSize(Model model, @RequestParam int sizeId) {
		
//		model.addAttribute("product",productService.findById(id));
		return "product-details";
	}
}




