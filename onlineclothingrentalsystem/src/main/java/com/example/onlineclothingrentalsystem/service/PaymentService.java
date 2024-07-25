package com.example.onlineclothingrentalsystem.service;

import java.util.List;

import com.example.onlineclothingrentalsystem.entity.Payment;


public interface PaymentService {
	Payment addPayment(Payment payment,long orderId,long userId);
	List<Payment> getAllPayments();
	Payment getPaymentById(long paymentId);
	void deletePayment(long paymentId);
	public List<Payment> getAllPaymentsByUserId(long userId);
}
