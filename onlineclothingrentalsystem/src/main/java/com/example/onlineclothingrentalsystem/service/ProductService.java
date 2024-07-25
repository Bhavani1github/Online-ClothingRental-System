package com.example.onlineclothingrentalsystem.service;

import java.util.List;
import com.example.onlineclothingrentalsystem.entity.Product;
import com.example.onlineclothingrentalsystem.entity.ProductCategory;
import com.example.onlineclothingrentalsystem.entity.ProductPaging;

public interface ProductService {
	Product addProduct(Product p);

    List<Product> getAllProduct();

    Product getProductById(long productId); // Added method

    Product updateProduct(long id, Product p); // Updated method signature

    void deleteProductById(long id);
	ProductPaging getAllPageProduct(int pageNo, int pageSize);  

    
    // Uncomment and implement these methods if needed
    // List<Product> findByCategory(ProductCategory category);
    // DressPaging findByCategory(ProductCategory category, Integer pageNo, Integer pageSize);
    // DressPaging getAllDress(Integer pageNo, Integer pageSize);
    // List<Product> findByMrpPrice(double mrpPrice);
    // DressPaging findByDressname(String keyword, Integer pageNo, Integer pageSize);
}
