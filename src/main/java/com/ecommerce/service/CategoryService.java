package com.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ecommerce.entity.Category;
import com.ecommerce.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {

	final CategoryRepository categoryRepository;
	
	public List<Category> getCategories() {
		return categoryRepository.findAll();
	}
	
	public void addCategory(Category category) {
		categoryRepository.save(category);
	}
	
	public void removeCategoryById(Integer id) {
		categoryRepository.deleteById(id);
	}
	
	public Optional<Category> getCategoryById(Integer id) {
		return categoryRepository.findById(id);
	}
}
