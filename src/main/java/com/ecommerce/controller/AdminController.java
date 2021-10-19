package com.ecommerce.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.configData.uploadConfig;
import com.ecommerce.entity.Category;
import com.ecommerce.entity.Product;
import com.ecommerce.service.CategoryService;
import com.ecommerce.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AdminController {
	
	final CategoryService categoryService;
	final ProductService productService;
	
	@GetMapping(value = "/admin")
	public String adminHome() {
		return "adminHome";
	}
	
	@GetMapping(value = "/admin/categories")
	public String getCat(Model model) {
		model.addAttribute("categories", categoryService.getCategories());
		return "categories";
	}
	
	@GetMapping(value = "/admin/categories/add")
	public String getCatAdd(Model model) {
		model.addAttribute("category", new Category());		
		return "categoriesAdd";
	}
	
	@PostMapping(value = "/admin/categories/add")
	public String postCatAdd(@ModelAttribute("category") Category category) {
		categoryService.addCategory(category);
		return "redirect:/admin/categories";
	}
	
	@GetMapping(value = "/admin/categories/delete/{id}")
	public String removeCatById(@PathVariable("id") Integer category_id) {
		categoryService.removeCategoryById(category_id);
		return "redirect:/admin/categories";
	}
	
	@GetMapping(value = "/admin/categories/update/{id}")
	public String updateCategory(@PathVariable("id") Integer category_id, Model model) {
		Optional<Category> categoryOptional = categoryService.getCategoryById(category_id);
		if(categoryOptional.isPresent()) {
			model.addAttribute("category", categoryOptional.get());
			return "categoriesAdd";
		}else {
			return "404";
		}
	}
	
	@GetMapping(value = "/admin/products")
	public String getAllProducts(Model model) {
		model.addAttribute("products", productService.getAllProducts());
		return "products";
	}
	@GetMapping(value = "/admin/products/add")
	public String addProduct(Model model) {
		model.addAttribute("product", new Product());
		model.addAttribute("categories", categoryService.getCategories());	
		return "productsAdd";
	}
	@PostMapping(value = "/admin/products/add")
	public String postProduct(@ModelAttribute("product") Product product, @RequestParam(value = "productImage", required = false) MultipartFile file, 
			@RequestParam(value = "imgName", required = false) String imageName) throws Throwable {
		
		productService.addProduct(product, file, imageName);
		return "redirect:/admin/products";
	}
	
	@GetMapping(value = "/admin/product/delete/{id}")
	public String removeProdById(@PathVariable("id") Long id) {
		productService.removeProductById(id);
		return "redirect:/admin/products";
	}
	
	@GetMapping(value = "/admin/product/update/{id}")
	public String updateProduct(@PathVariable("id") Long id, Model model) throws Throwable {
		Optional<Product> productOptional = productService.getProductById(id);
		if(productOptional.isPresent()) {
			Category category = productOptional.get().getCategory();
			category.setProducts(null);
			productOptional.get().setCategory(category);
			model.addAttribute("product", productOptional.get());
			model.addAttribute("categories", categoryService.getCategories());
			return "productsAdd";
		}else {
			return "404";
		}
	}
}
