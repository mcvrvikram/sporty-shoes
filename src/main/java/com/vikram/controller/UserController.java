package com.vikram.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vikram.dto.CartDto;
import com.vikram.dto.OrderDetailsDto;
import com.vikram.entity.Cart;
import com.vikram.entity.CartQuantity;
import com.vikram.entity.Invoice;
import com.vikram.entity.Product;
import com.vikram.entity.Size;
import com.vikram.entity.User;
import com.vikram.security.MyUserDetails;
import com.vikram.service.CartQuantityService;
import com.vikram.service.CartService;
import com.vikram.service.InvoiceService;
import com.vikram.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
		
	@Autowired
	private CartQuantityService cartQuantityService;

	@Autowired
	private InvoiceService invoiceService;
	
	@Autowired
	private CartService cartService;
	
	@PostMapping("/cart")
	public String addToCart(Model model,@AuthenticationPrincipal MyUserDetails userDetails, @ModelAttribute CartQuantity cartQuantity) {
		
		User user = userDetails.getUser();
		if(user!=null) {
			cartService.saveOrUpdate(cartQuantity, user);
//			return "<h1>Success Admin</h1>";
			return "redirect:/default/list?success";
		}
		return "<h1>invaid</h1>";
	}
	
	
	@GetMapping("/cart")
	public String showCart(Model model, @AuthenticationPrincipal MyUserDetails userDetails) {
		int total=0;
		User user =  userService.findById(userDetails.getUser().getId());
		List<CartDto> cartDtos = new ArrayList<CartDto>();
		for (Cart cart: user.getCart()) {
			if(!cart.isCheckedOut()) {
				Set<CartQuantity> cartQuantities = cart.getCartQuantity();
				for (CartQuantity cartQuantity:cartQuantities) {
					CartDto cartDto = new CartDto();
					cartDto.setCartQuantityId(cartQuantity.getId());
					cartDto.setQuantity(cartQuantity.getQuantity());
					Size size = cartQuantity.getProductSize();
					cartDto.setSize(size.getProductSize().toString());
					Product product = size.getProduct();
					cartDto.setProductName(product.getName());
					cartDto.setImgPath(product.getImagePath());
					cartDto.setPrice(Integer.parseInt(product.getPrice()));
					total+=Integer.parseInt(product.getPrice())*cartQuantity.getQuantity();
					cartDtos.add(cartDto);
				}
			}
		}
		model.addAttribute("total", total);
		model.addAttribute("cartItems", cartDtos);
		return "cart";
	}
	
	@GetMapping("/removeCartItem")
	public String removeCartItem(@RequestParam int cartItemId) {
		
		cartQuantityService.deleteById(cartItemId);
		return "redirect:/user/cart";
	}
	
	@GetMapping("/checkOut")
	public String checkOut(Model model) {
		model.addAttribute("invoice", new Invoice());
		return "add-address";
	}
	
	@PostMapping("/buy")
	public String placeOrder(@ModelAttribute Invoice invoice, @AuthenticationPrincipal MyUserDetails userDetails) {
		Cart cart = new Cart();
		User user =  userService.findById(userDetails.getUser().getId());
		for(Cart eachCart: user.getCart()) {
			if(!eachCart.isCheckedOut()) {
				cart=eachCart;
				break;
			}
			
		}
		invoiceService.save(invoice, cart);
		return "redirect:/user/order-details?success";
	}
	
	@GetMapping("/order-details")
	public String orderDetails(@AuthenticationPrincipal MyUserDetails userDetails, Model model) {
		
		User user =  userService.findById(userDetails.getUser().getId());
		model.addAttribute("orders",userService.findAllOrders(user));
		return "user-order-details";
	}
	
	@GetMapping("/view-order")
	public String viewOrder(@AuthenticationPrincipal MyUserDetails userDetails, Model model, @RequestParam int invoiceId) {
		
		User user =  userService.findById(userDetails.getUser().getId());
		for(OrderDetailsDto each:userService.findAllOrders(user)) {
			if(each.getInvoiceId()==invoiceId) {
				model.addAttribute("orderDetails", each);
			}
				
		}
		return "view-order";
	}
	
	@GetMapping("/profile")
	public String showProfile(@AuthenticationPrincipal MyUserDetails userDetails, Model model){
		model.addAttribute("user", userService.findById(userDetails.getUser().getId()));
		
		return "profile";
		
	}
	
	@GetMapping("/changePassword")
	public String changePassword(Model model) {
		
		return "change-password";
		
	}
	
	@PostMapping("/changePassword")
	public String changePasswordValidation(@AuthenticationPrincipal MyUserDetails userDetails, Model model,@RequestParam String newPwd ,@RequestParam String confirmPwd) {
		if(newPwd.equals(confirmPwd)) {
			User user =  userService.findById(userDetails.getUser().getId());
			userService.changePwd(user,newPwd );
			return "redirect:/user/profile?success";
		}
		
		
		return "redirect:/user/changePassword?error";
		
	}
	
}
