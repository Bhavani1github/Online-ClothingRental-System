package com.example.onlineclothingrentalsystem.service;

import java.util.List;
import com.example.onlineclothingrentalsystem.entity.Cart;
import com.example.onlineclothingrentalsystem.entity.User;

public interface CartService {
    Cart addCart(Cart cart, long productId, long userId);
    List<Cart> getAllCarts();
    Cart getCartById(long cartId);
    Cart updateCart(Cart cart, long cartId);
    void deleteCart(long cartId);
    void deleteCartByUser(User user);
}
