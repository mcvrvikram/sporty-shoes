package com.vikram.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.vikram.dto.CartDto;
import com.vikram.dto.OrderDetailsDto;
import com.vikram.dto.UserRegistrationDto;
import com.vikram.entity.Cart;
import com.vikram.entity.CartQuantity;
import com.vikram.entity.CustomRole;
import com.vikram.entity.Role;
import com.vikram.entity.User;
import com.vikram.repository.RoleRepository;
import com.vikram.repository.UserRepository;
import com.vikram.security.MyUserDetails;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	private RoleRepository roleRepository;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	Logger logger = LoggerFactory.getLogger(this.getClass());

	public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {

		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}

	@Override
	public User save(UserRegistrationDto userRegistrationDto) {

		User user = new User();
		user.setEmail(userRegistrationDto.getEmail());
		user.setFirstName(userRegistrationDto.getFirstName());
		user.setLastName(userRegistrationDto.getLastName());
		user.setPassword(passwordEncoder.encode(userRegistrationDto.getPassword()));
		Set<Role> roles = new HashSet<Role>();
		roles.add(roleRepository.findByName(CustomRole.ROLE_USER));
		user.setRole(roles);
		return userRepository.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid crdentials");
		}
		return new MyUserDetails(user);
	}

	@Override
	public User findById(long id) {
		Optional<User> findUser = userRepository.findById(id);
		if (findUser.isPresent()) {
			return findUser.get();
		}

		return null;
	}

	@Override
	public List<OrderDetailsDto> findAllOrders(User user) {

		List<OrderDetailsDto> orders = new ArrayList<OrderDetailsDto>();
		for (Cart cart : user.getCart()) {
			if (cart.isCheckedOut()) {
				OrderDetailsDto eachOrder = new OrderDetailsDto();
				List<CartDto> cartDtos = new ArrayList<CartDto>();
				eachOrder.setTotal(cart.getTotal());
				eachOrder.setInvoiceId(cart.getInvoice().getId());
				eachOrder.setLine1(cart.getInvoice().getLine1());
				eachOrder.setLine2(cart.getInvoice().getLine2());
				eachOrder.setZipcode(cart.getInvoice().getZipcode());
				eachOrder.setPhone(cart.getInvoice().getPhone());
				eachOrder.setDate(cart.getInvoice().getDate());
				eachOrder.setDelivered(cart.getInvoice().isDelivered());
				for (CartQuantity cartQuantity : cart.getCartQuantity()) {
					logger.info("====>Inside Cart Details");
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

		}
		return orders;
	}

	@Override
	public List<User> findAll() {

		return userRepository.findAll();
	}

	@Override
	public List<User> findByFirstNameIgnoreCaseContainingOrLastNameIgnoreCaseContainingOrEmailIgnoreCaseContaining(
			String firstName, String lastName, String email) {

		return userRepository
				.findByFirstNameIgnoreCaseContainingOrLastNameIgnoreCaseContainingOrEmailIgnoreCaseContaining(firstName,
						lastName, email);
	}

	@Override
	public User changePwd(User user, String newPwd) {
		user.setPassword(passwordEncoder.encode(newPwd));
		return userRepository.save(user);
	}

}
