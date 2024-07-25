package com.example.onlineclothingrentalsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.onlineclothingrentalsystem.entity.Order;

@Repository
public interface OrderRepository  extends JpaRepository<Order, Long>{
	public List<Order> findByUserId(long userId);
	public void deleteByOrderId(long orderId);

}
