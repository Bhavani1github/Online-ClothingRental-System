package com.example.onlineclothingrentalsystem.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.onlineclothingrentalsystem.entity.ProductCategory;


public interface ProductCategoryRepository  extends JpaRepository<ProductCategory, Integer>{

}
