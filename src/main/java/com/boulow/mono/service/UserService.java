package com.boulow.mono.service;

import java.util.List;
import java.util.Optional;

import com.boulow.mono.entity.User;
import com.boulow.mono.entity.dto.UserUpdateDTO;

public interface UserService {

	Optional<User> findById(Long id);
	
	List<User> findAll();
	
	Optional<User> findByUsername(String username);
	
	Optional<User> findByEmail(String email);
	
	List<User> findByFirstNameContainingIgnoreCase(String firstname);
	
	List<User> findByLastNameContainingIgnoreCase(String lastname);
	
	List<User> findByIsActiveTrue();
	
	Boolean existsByUsername(String username);
	
	User save(User user);
	
	User update(User fromUser, UserUpdateDTO toUser);
	
	void delete(Long id);
}
