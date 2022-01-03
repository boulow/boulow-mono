package com.boulow.mono.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.boulow.mono.entity.Address;
import com.boulow.mono.enumeration.AddressType;

@Repository
public interface AddressRepository extends CrudRepository<Address, Long>{
	
	@Override
	Optional<Address> findById(Long id);
	
	Iterable<Address> findByType(AddressType type);
	
	@Override
	Iterable<Address> findAll();

}
