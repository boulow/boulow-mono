package com.boulow.mono.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.boulow.mono.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
	
	@Override
	Optional<User> findById(Long id);
	
	@Override
	List<User> findAll();
	
	Optional<User> findByUsername(String username);
	
	Optional<User> findByEmail(String email);
	
	List<User> findByFirstNameContainingIgnoreCase(String firstname);
	
	List<User> findByLastNameContainingIgnoreCase(String lastname);
	
	List<User> findByIsActiveTrue();
	
	Boolean existsByUsername(String username);
	
	@Override
	void deleteById(Long id);

}
