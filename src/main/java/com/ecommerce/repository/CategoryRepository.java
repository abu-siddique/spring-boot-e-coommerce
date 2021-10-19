package com.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{

}
