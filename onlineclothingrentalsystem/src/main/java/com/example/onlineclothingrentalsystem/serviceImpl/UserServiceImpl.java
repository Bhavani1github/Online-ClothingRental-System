package com.example.onlineclothingrentalsystem.serviceImpl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.onlineclothingrentalsystem.entity.User;
import com.example.onlineclothingrentalsystem.exception.ResourseNotFoundException;
import com.example.onlineclothingrentalsystem.repository.UserRepository;
import com.example.onlineclothingrentalsystem.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	 @Autowired
	    private UserRepository userRepository;

	   @Override
	     public User registerUser(User user) {
	        return userRepository.save(user);
	    }

	    @Override
	    public User getUserById(long id) {
	        return userRepository.findById(id)
	                .orElseThrow(() -> new ResourseNotFoundException("User", "Id", id));
	    }

	    @Override
	     public void deleteUserById(long id) {
	        userRepository.deleteById(id);
	    } 

	    @Override
	     public List<User> getAllUsers() {
	        return userRepository.findAll();
	    } 
}
