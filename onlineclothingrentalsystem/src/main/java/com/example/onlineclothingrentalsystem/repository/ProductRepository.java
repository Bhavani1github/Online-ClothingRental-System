package com.example.onlineclothingrentalsystem.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.onlineclothingrentalsystem.entity.Product;
import com.example.onlineclothingrentalsystem.entity.ProductCategory;
import com.example.onlineclothingrentalsystem.entity.ProductPaging;



@Repository 
public interface ProductRepository extends JpaRepository<Product, Long> {

	Optional<Product> findById(long productId);
	public List<Product> findByPnameContains(String keyword);

}
