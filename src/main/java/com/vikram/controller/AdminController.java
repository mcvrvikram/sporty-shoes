package com.vikram.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vikram.dto.OrderDetailsDto;
import com.vikram.entity.Invoice;
import com.vikram.service.CategoryService;
import com.vikram.service.InvoiceService;
import com.vikram.service.ProductService;
import com.vikram.service.UserService;


@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private InvoiceService invoiceService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	ProductService productService;
	
	@GetMapping("/dashboard")
	public String adminHome(Model model) {
		model.addAttribute("invoices", invoiceService.findAll().size());
		model.addAttribute("users", userService.findAll().size());
		return "dashboard";
	}
	
	@GetMapping("/user-list")
	public String findAllUsers(Model model) {
		model.addAttribute("users", userService.findAll());
		return "user-list";
	}
	
	@PostMapping("/user/search")
	public String findUser(Model model, @RequestParam String input) {
		model.addAttribute("users",
				userService.findByFirstNameIgnoreCaseContainingOrLastNameIgnoreCaseContainingOrEmailIgnoreCaseContaining(input, input, input));
		
		return "user-list";
	}
	
	@GetMapping("/all-orders")
	public String AllOrders(Model model) {
		
		model.addAttribute("orders", invoiceService.findAllOrders());
		return "order-details";
	}
	
	@GetMapping("/reports")
	public String purchaseReports(Model model) {
		model.addAttribute("categories", categoryService.findAll());
		return "reports-form";
		
	}
	
	@PostMapping("/report-byDate")
	public String reportsByDate(@RequestParam String fromDate  ,@RequestParam String toDate,Model model ) {
		LocalDate from=LocalDate.parse(fromDate);
		LocalDate to=LocalDate.parse(toDate);
		model.addAttribute("orders", invoiceService.findByDate(from,to));
		return "order-details";
	}
	
	@GetMapping("/report-byCategory")
	public String reportsByCat(@RequestParam String category, Model model) {
		
		model.addAttribute("orders",invoiceService.findByCategory(Integer.parseInt(category)));
		return "order-details";
	}
	
	
	@GetMapping("/report-byDate-view")
	public String viewOrder(Model model, @RequestParam int invoiceId, @RequestParam String fromDate  ,@RequestParam String toDate ) {
		
		LocalDate from=LocalDate.parse(fromDate);
		LocalDate to=LocalDate.parse(toDate);
		for(OrderDetailsDto each:invoiceService.findByDate(from,to)) {
			if(each.getInvoiceId()==invoiceId) {
				model.addAttribute("orderDetails", each);
			}
				
		}
		return "admin-purchase-result-view";
	}
	
	@GetMapping("/setDelivered")
	public String setDelivered(@RequestParam int invoiceId) {
		Invoice invoice = invoiceService.findById(invoiceId);
		invoice.setDelivered(true);
		invoiceService.save(invoice);
		return "redirect:/admin/all-orders";
	}
	
	
		
	
}




