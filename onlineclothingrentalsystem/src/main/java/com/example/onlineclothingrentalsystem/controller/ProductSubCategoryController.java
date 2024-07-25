package com.example.onlineclothingrentalsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.onlineclothingrentalsystem.entity.ProductSubCategory;
import com.example.onlineclothingrentalsystem.repository.ProductSubCategoryRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/productsubcategory")
public class ProductSubCategoryController {
	@Autowired
	private ProductSubCategoryRepository pcr;

	@PostMapping("/add")
	public String addProductSubCategory(@RequestBody ProductSubCategory p) {
		pcr.save(p);
		return "Product sub category added successfully";
	}

	@GetMapping("/list")
	public List<ProductSubCategory> getAllProduct() {
		return pcr.findAll();
	}

	@GetMapping("/getbyid/{id}")
	public ProductSubCategory getProductSubCategoryById(@PathVariable int id) {
		return pcr.getById(id);
	}

	@DeleteMapping("/delete/{id}")
	public String deleteProductSubCategoryById(@PathVariable int id) {
		pcr.deleteById(id);
		return "product deleted successfully";
	}

}
