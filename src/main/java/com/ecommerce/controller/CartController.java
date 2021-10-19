package com.ecommerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ecommerce.global.GlobalData;
import com.ecommerce.entity.Product;
import com.ecommerce.service.CategoryService;
import com.ecommerce.service.ProductService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CartController {

	final ProductService productService;

	@GetMapping(value = "/addToCart/{id}")
	public String addToCart(@PathVariable("id") Long id) {
		GlobalData.cart.add(productService.getProductById(id).get());
		return "redirect:/shop";
	}
	
	@GetMapping(value = "/cart")
	public String getCart(Model model) {
		model.addAttribute("cartCount", GlobalData.cart.size());
		model.addAttribute("total", GlobalData.cart.stream().mapToDouble(pro -> pro.getPrice().doubleValue()).sum());
		model.addAttribute("cart", GlobalData.cart);
		return "cart";
	}
	
	@GetMapping(value = "/cart/removeItem/{index}")
	public String removeItem(@PathVariable("index") int idx) {
		GlobalData.cart.remove(idx);
		return "redirect:/cart";
	}
	
	@GetMapping(value = "/checkout")
	public String checkout(Model model) {
		model.addAttribute("total", GlobalData.cart.stream().mapToDouble(pro -> pro.getPrice().doubleValue()).sum());
		return "checkout";
	}
	
	@PostMapping(value = "/payNow")
	public String payNow(RedirectAttributes attributes) {
		attributes.addFlashAttribute("success", "Success bhai");
		return "orderPlaced";
	}
}
