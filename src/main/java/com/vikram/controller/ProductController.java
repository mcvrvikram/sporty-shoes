package com.vikram.controller;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.vikram.entity.CartQuantity;
import com.vikram.entity.Gender;
import com.vikram.entity.Product;
import com.vikram.entity.Size;
import com.vikram.entity.SizeChart;
import com.vikram.entity.User;
import com.vikram.security.MyUserDetails;
import com.vikram.service.CartService;
import com.vikram.service.CategoryService;
import com.vikram.service.ProductService;
import com.vikram.service.SizeService;
import com.vikram.util.FileUploadUtil;

@Controller
@RequestMapping("/admin/product")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	SizeService sizeService;
	
	@Autowired
	CartService cartService;
	

	@GetMapping("/list")
	public String productList(Model model) {
		model.addAttribute("products", productService.findAll());
		return "admin-list-products";
	}
	
	@GetMapping("/add")
	public String showProductForm(Model model) {
		model.addAttribute("gender", Gender.values());
		model.addAttribute("product", new Product());	
		model.addAttribute("categories", categoryService.findAll());
		return "product-form";
	}
	
	@PostMapping("/add")
    public String saveProduct(@RequestParam("file") MultipartFile file,@RequestParam("category") String categoryId,
    						@ModelAttribute("product") Product product) throws IOException {
		 
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		product.setCategory(categoryService.findById(Integer.parseInt(categoryId)));
		product.setImage(fileName);
        Product savedProduct = productService.saveOrUpdate(product);
        String uploadDir = "uploads/" + savedProduct.getId();
        FileUploadUtil.saveFile(uploadDir, fileName, file);
        	return "redirect:/admin/product/quantity?productId="+product.getId();
               
    }
	
	@GetMapping("/quantity")
	public String showSizeForm(Model model,@RequestParam("productId") int productId) {
		
		model.addAttribute("size",new Size());
		model.addAttribute("sizeChart", SizeChart.values());
		model.addAttribute("product", productService.findById(productId));
		return "size-form";
	}
	
	@PostMapping("/quantity")
	public String addQuantity(Model model,@RequestParam("productId") int productId, @ModelAttribute("size") Size size) {
		
		Product product = productService.findById(productId);
		size.setProduct(product);
		sizeService.saveOrUpdate(size);
		
		return "redirect:/admin/product/quantity?productId="+productId;
	}
	
	
}





