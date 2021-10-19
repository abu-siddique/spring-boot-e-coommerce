package com.ecommerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecommerce.global.*;
import com.ecommerce.service.CategoryService;
import com.ecommerce.service.ProductService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class HomeController {

	final CategoryService categoryService;
	final ProductService productService;
	
	@GetMapping(value = {"/","/home"})
	public String Home(Model model) {
		model.addAttribute("cartCount", GlobalData.cart.size());
		return "index";
	}
	
	@GetMapping(value = "/shop")
	public String Shop(Model model) {
		model.addAttribute("cartCount", GlobalData.cart.size());
		model.addAttribute("categories", categoryService.getCategories());
		model.addAttribute("products", productService.getAllProducts());
		return "shop";
	}
	
	@GetMapping(value = "/shop/category/{id}")
	public String ShopByCat(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("cartCount", GlobalData.cart.size());
		model.addAttribute("categories", categoryService.getCategories());
		model.addAttribute("products", productService.getAllProductsByCat(id));
		return "shop";
	}
	
	@GetMapping(value = "/shop/viewproduct/{id}")
	public String viewProduct(@PathVariable("id") Long id, Model model) {
		model.addAttribute("product", productService.getProductById(id).get());
		return "viewProduct";
	}
}
