package com.example.onlineclothingrentalsystem.service;

import java.util.List;

import com.example.onlineclothingrentalsystem.entity.Order;
import com.example.onlineclothingrentalsystem.entity.TransactionDetails;



public interface OrderService {
	Order addOrder(Order order, long userId, long cartId);

	Order getOrderById(long orderId);

	Order updateOrder(Order order, long orderId);

	List<Order> getOrderByUserId(long userId);

	
	Order addOrderItem(Order order,long userId);
	
	void deleteOrder(long orderId);
	
	TransactionDetails createTransaction(double amount);

	List<Order> getAllOrders();

}
