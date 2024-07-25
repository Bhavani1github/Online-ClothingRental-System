package com.example.onlineclothingrentalsystem.entity;

import java.util.List;



public class ProductPaging {
	private List<Product> product;
	public List<Product> getProduct() {
		return product;
	}
	public void setProduct(List<Product> product) {
		this.product = product;
	}
	
	public long getTotalProduct() {
		return totalProduct;
	}
	public void setTotalProduct(long totalProduct) {
		this.totalProduct = totalProduct;
	}

	private long totalProduct;
	

	
//	public long getTotal() {
//		return totalProducts;
//	}
//	public void setTotalDress(long totalDress) {
//		this.totalDress = totalDress;
//	} 
}
