package com.ecommerce.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.configData.uploadConfig;
import com.ecommerce.entity.Category;
import com.ecommerce.entity.Product;
import com.ecommerce.repository.CategoryRepository;
import com.ecommerce.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

	final ProductRepository productRepository;
	final CategoryRepository categoryRepository;
	final uploadConfig uploadConfig;
	public static String uploadDir = System.getProperty("user.dir")+"/src/main/resources/static/productImages/";
	
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}
	
	public void addProduct(Product product, MultipartFile file, String imageName) throws Throwable {
		String imageUUID = null;
		if(file != null && !file.isEmpty()) {
			imageUUID = file.getOriginalFilename();
			if (!Files.exists(Paths.get(uploadDir))) {
			    Files.createDirectories(Paths.get(uploadDir));
			}
			InputStream fis = file.getInputStream();
			File fil = new File(Paths.get(uploadDir).toString(), imageUUID);
			OutputStream os = new FileOutputStream(fil);
			FileCopyUtils.copy(fis, os);
		}else {
			imageUUID = imageName;
		}
		product.setImagename(imageUUID);
		productRepository.save(product);
	}
	
	public void removeProductById(Long id) {
		productRepository.deleteById(id);
	}
	
	public Optional<Product> getProductById(Long id) {
		return productRepository.findById(id);
	}
	
	public List<Product> getAllProductsByCat(int id) {
		Optional<Category> category = categoryRepository.findById(id);
		if(category.isPresent())
			return productRepository.findByCategory(category.get());
		return new ArrayList<Product>();
	}
}
