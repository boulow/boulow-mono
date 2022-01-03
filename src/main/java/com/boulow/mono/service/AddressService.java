package com.boulow.mono.service;

import java.util.Optional;

import com.boulow.mono.entity.Address;
import com.boulow.mono.enumeration.AddressType;

public interface AddressService {

	Optional<Address> findById(Long id);
	
	Iterable<Address> findByType(AddressType type);

	Iterable<Address> findAll();
	
	Address save(Address address);
	
	void update(Address obsolete, Address latest);
}
