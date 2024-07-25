package com.example.onlineclothingrentalsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.onlineclothingrentalsystem.entity.Cart;
import com.example.onlineclothingrentalsystem.entity.User;



@Repository
public interface CartRepository extends JpaRepository<Cart, Long>{
	void deleteCartByUser(User u);
}
