package com.example.onlineclothingrentalsystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long pid;

//	@NotNull(message = "Please Give Unique Product Name")
	private String pname;

//	@NotNull(message = "Please Give Unique Product Description")
	private String pDescription;

//	@NotNull(message = "Please Give  Product Category")
	// private String pCategory;

//	@NotNull(message = "Please Give Product Quantity")
	private int pQuantity;
	
	// @NotNull(message = "Please Give Unique Product Image URL")
	private String pImage;

	@ManyToOne
	@JoinColumn(name = "pc_id")
	private ProductCategory productCategory;

	@ManyToOne
	@JoinColumn(name = "psc_id")
	private ProductSubCategory subCategory;

	@Column(name = "mrp_price", nullable = false)
	private double mrpPrice;

	public long getPid() {
		return pid;
	}

	public void setPid(long pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getpDescription() {
		return pDescription;
	}

	public void setpDescription(String pDescription) {
		this.pDescription = pDescription;
	}

	public String getpImage() {
		return pImage;
	}

	public void setpImage(String pImage) {
		this.pImage = pImage;
	}

	public ProductCategory getProductCategory() {
		return productCategory;
	}

	public ProductSubCategory getSubCategory() {
		return subCategory;
	}

	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}

	public void setSubCategory(ProductSubCategory subCategory) {
		this.subCategory = subCategory;
	}

	public double getMrpPrice() {
		return mrpPrice;
	}

	public void setMrpPrice(double mrpPrice) {
		this.mrpPrice = mrpPrice;
	}
	
	public int getpQuantity() {
		return pQuantity;
	}

	public void setpQuantity(int pQuantity) {
		this.pQuantity = pQuantity;
	}


}
