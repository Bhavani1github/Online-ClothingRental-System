package com.example.onlineclothingrentalsystem.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.onlineclothingrentalsystem.entity.User;
import com.example.onlineclothingrentalsystem.repository.UserRepository;


@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:4200")

public class UserController {

	@Autowired
	private UserRepository ur;

	

	@PostMapping
	public ResponseEntity<User> registerUser(@RequestBody User user) {
		return new ResponseEntity<User>(ur.save(user), HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable long id) {
		User user = ur.findById(id).get();
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUserById(@PathVariable long id) {
		ur.deleteById(id);
		return new ResponseEntity<String>("The User is deleted successfully", HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> users = ur.findAll();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}


	@PostMapping("login")
	public ResponseEntity<User> loginUser(@RequestBody User user) {
		return new ResponseEntity<User>(ur.findByEmailAndPass(user.getEmail(), user.getPass()), HttpStatus.OK);
	}
	
	@GetMapping("/findByUserEmail/{email}")
	public User getUserByEmail(@PathVariable String email) {
		System.out.println(">>>>" + email);
		return ur.findByEmail(email);
	}
	
	// Change Password
	@PostMapping("/changePassword/{uid}/{newpassword}")
	public User changeUserPassword(@PathVariable("uid") Long uid,@PathVariable("newpassword") String newpassword) {
		User u = ur.findById(uid).get();
		u.setPass(newpassword);
		ur.save(u);
		return u;
	}
	
}