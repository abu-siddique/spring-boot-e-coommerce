package com.ecommerce.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecommerce.entity.Role;
import com.ecommerce.entity.User;
import com.ecommerce.global.GlobalData;
import com.ecommerce.repository.RoleRepository;
import com.ecommerce.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LoginConroller {

	final BCryptPasswordEncoder bCryptPasswordEncoder;
	final UserRepository userRepository;
	final RoleRepository roleRepository;
	
	@GetMapping(value = "/login")
	public String login() {
		GlobalData.cart.clear();
		return "login";
	}
	
	@GetMapping(value = "/register")
	public String register() {
		return "register";
	}
	
	@PostMapping(value = "/register")
	public String postRegister(@ModelAttribute("user") User user, HttpServletRequest request) throws ServletException {
		String pass = user.getPassword();
		user.setPassword(bCryptPasswordEncoder.encode(pass));
		List<Role> list = new ArrayList<Role>();
		list.add(roleRepository.findById((long)2).get());
		user.setRoles(list);
		user = userRepository.save(user);
		//request.login(user.getEmail(), user.getPassword());
		return "redirect:/login";
	}
}
