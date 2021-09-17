package com.vikram.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vikram.dto.CartDto;
import com.vikram.dto.OrderDetailsDto;
import com.vikram.entity.Cart;
import com.vikram.entity.CartQuantity;
import com.vikram.entity.Invoice;
import com.vikram.repository.CartRepository;
import com.vikram.repository.InvoiceRepository;

@Service
public class InvoiceServiceImpl implements InvoiceService {
	
	@Autowired
	private InvoiceRepository invoiceRepository;
	
	@Autowired
	private CartRepository cartRepository;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public Invoice findById(int id) {
		Optional<Invoice> find = invoiceRepository.findById(id);
		if(find.isPresent())
			return find.get();
		return null;
	}

	@Override
	public List<Invoice> findAll() {
		
		return invoiceRepository.findAll();
	}

	@Override
	public Invoice save(Invoice invoice, Cart cart) {
		Optional<Cart> cartOptional = cartRepository.findById(cart.getId());
		if(cartOptional.isPresent()) {
			Cart cartPersisted = cartOptional.get();
			cartPersisted.setInvoice(invoice);
			cartPersisted.setCheckedOut(true);
			invoice.setCart(cartPersisted);
	//		cartRepository.save(cart);
			return invoiceRepository.save(invoice);
		}
		return null;
	}
	@Override
	public List<OrderDetailsDto> invoiceGenerator(List<Invoice> invoices){
		List<OrderDetailsDto> orders = new ArrayList<OrderDetailsDto>();
		for(Invoice eachInvoice : invoices ) {
			OrderDetailsDto eachOrder = new OrderDetailsDto();
			List<CartDto> cartDtos = new ArrayList<CartDto>();
			eachOrder.setDate(eachInvoice.getDate());
			eachOrder.setDelivered(eachInvoice.isDelivered());
			eachOrder.setEmail(eachInvoice.getCart().getUser().getEmail());
			eachOrder.setLine1(eachInvoice.getLine1());
			eachOrder.setLine2(eachInvoice.getLine2());
			eachOrder.setZipcode(eachInvoice.getZipcode());
			eachOrder.setPhone(eachInvoice.getPhone());
			eachOrder.setTotal(eachInvoice.getCart().getTotal());
			eachOrder.setInvoiceId(eachInvoice.getId());
			
			for(CartQuantity cartQuantity:eachInvoice.getCart().getCartQuantity()) {
				
				CartDto cartDto = new CartDto();
				cartDto.setQuantity(cartQuantity.getQuantity());
				cartDto.setSize(cartQuantity.getProductSize().getProductSize().toString());
				cartDto.setProductName(cartQuantity.getProductSize().getProduct().getName());
				cartDto.setImgPath(cartQuantity.getProductSize().getProduct().getImagePath());
				cartDto.setPrice(Integer.parseInt(cartQuantity.getProductSize().getProduct().getPrice()));
				cartDtos.add(cartDto);
			}
			eachOrder.setCartDtos(cartDtos);
			orders.add(eachOrder);
		}
		return orders;
	}

	@Override
	public List<OrderDetailsDto> findByDate(LocalDate fromDate, LocalDate toDate) {
		List<Invoice> invoices = invoiceRepository.findAllByDateBetween(fromDate, toDate);
		
		return invoiceGenerator(invoices);
	}

	@Override
	public List<OrderDetailsDto> findByCategory(int categoryId) {
		List<Invoice> allInvoices = invoiceRepository.findAll();
		List<Invoice> invoices = new ArrayList<Invoice>();
		for(Invoice eachInvoice : allInvoices ) {
			for(CartQuantity cartQuantity:eachInvoice.getCart().getCartQuantity()) {
				
				if(cartQuantity.getProductSize().getProduct().getCategory().getId()==categoryId) {
					invoices.add(eachInvoice);
					break;
				}
			}
		}
			
		
		return invoiceGenerator(invoices);
	}

	@Override
	public Invoice save(Invoice invoice) {
		
		return invoiceRepository.save(invoice);
	}

	@Override
	public List<OrderDetailsDto> findAllOrders() {
		
		return invoiceGenerator(invoiceRepository.findAll());
	}

}
