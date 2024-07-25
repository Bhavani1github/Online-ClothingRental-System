package com.example.onlineclothingrentalsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.onlineclothingrentalsystem.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	public User findByEmailAndPass(String email, String pass);

	public User findByEmail(String email);
//	public User findByUsername(String username);
//	List<User> findByFirstNameContainingIgnoreCase(String searchText);
}
