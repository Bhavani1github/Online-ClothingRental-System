package com.example.onlineclothingrentalsystem.serviceImpl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.onlineclothingrentalsystem.entity.Order;
import com.example.onlineclothingrentalsystem.entity.Payment;
import com.example.onlineclothingrentalsystem.entity.User;
import com.example.onlineclothingrentalsystem.exception.ResourseNotFoundException;
import com.example.onlineclothingrentalsystem.repository.OrderRepository;
import com.example.onlineclothingrentalsystem.repository.PaymentRepository;
import com.example.onlineclothingrentalsystem.service.ProductService;
import com.example.onlineclothingrentalsystem.service.OrderService;
import com.example.onlineclothingrentalsystem.service.PaymentService;
import com.example.onlineclothingrentalsystem.service.UserService;


@Service
public class PaymentServiceImpl implements PaymentService{
	@Autowired
	private PaymentRepository paymentRepository;
		
		@Autowired
		private OrderRepository orderRepository;

		
		@Autowired
		private UserService userService;
		
		@Autowired
		private OrderService orderService;


	        public PaymentServiceImpl(PaymentRepository paymentRepository, ProductService bookService,
				UserService userService,OrderService orderService) {
			super();
			this.paymentRepository = paymentRepository;
			
			this.userService = userService;
			this.orderService = orderService;

		}


			@Override
			public Payment addPayment(Payment payment, long orderId, long userId) {
				// TODO Auto-generated method stub
	        	Order order = orderRepository.findById(orderId)
						.orElseThrow(() -> new ResourseNotFoundException("Order", "orderId", orderId));
				System.out.println("****************"+order.getOrderId());
	        	payment.setOrderId(orderId);
				payment.setTotalPrice(order.getTotalPrice());
				payment.setPaidDate(LocalDate.now());
				payment.setPaidAmount(order.getTotalPrice());
				if (payment.getTotalPrice() == payment.getPaidAmount()) {
					order.setPaymentStatus("PAID");
					order.setOrderStatus("Delivered");
				} else {

					order.setPaymentStatus("NOT-PAID");
					order.setOrderStatus("payment pending");
				}
					  User user=userService.getUserById(userId);
				    	
				    	payment.setUser(user);
				    	
				    	
				    	     //return paymentRepository.save(payment);
				    	
				
				return paymentRepository.save(payment);
				
	        }
	              // order = orderService.getOrderById(orderId);
	        	//payment.setOrderId(order.getOrderId());
	        	//payment.setTotalPrice(payment.getTotalPrice());
	        	//payment.setPaidDate(payment.getPaidDate());
	  

			


			@Override
			public List<Payment> getAllPayments() {
				return paymentRepository.findAll();
			}


			@Override
			public Payment getPaymentById(long paymentId) {
				return paymentRepository.findById(paymentId).orElseThrow(()->new ResourseNotFoundException("Payement","Id",paymentId));
			}

			


			@Override
			public void deletePayment(long paymentId) {
				paymentRepository.findById(paymentId).orElseThrow(()->new ResourseNotFoundException("Payement","Id",paymentId));
				paymentRepository.deleteById(paymentId);
				
				
			}


			@Override
			public List<Payment> getAllPaymentsByUserId(long userId) {
				return paymentRepository.findByOrderId(userId);
			}
}
