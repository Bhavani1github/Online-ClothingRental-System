package com.example.onlineclothingrentalsystem.entity;
import java.util.HashMap;
import java.util.Map;
import java.util.HashSet;
import java.util.Set;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "ProductCategory")
public class ProductCategory {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int pc_id;

	    public int getPc_id() {
		return pc_id;
	}

	public void setPc_id(int pc_id) {
		this.pc_id = pc_id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Set<ProductSubCategory> getSubCategories() {
		return subCategories;	
	}

	public void setSubCategories(Set<ProductSubCategory> subCategories) {
		this.subCategories = subCategories;
	}

		@Column
	    private String categoryName;

	    @OneToMany(fetch = FetchType.EAGER)
	    @JoinColumn(name = "pc_id")
	    private Set<ProductSubCategory> subCategories = new HashSet<>();


}
