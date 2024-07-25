package com.example.onlineclothingrentalsystem.service;

import java.util.List;

import com.example.onlineclothingrentalsystem.entity.Cart;
import com.example.onlineclothingrentalsystem.entity.Product;
import com.example.onlineclothingrentalsystem.entity.User;

import java.util.List;

import com.example.onlineclothingrentalsystem.entity.User;

public interface UserService {

    User registerUser(User user);
//
   User getUserById(long userId);
//
    void deleteUserById(long id);
//
    List<User> getAllUsers();
}
