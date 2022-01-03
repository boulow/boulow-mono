package com.boulow.mono.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boulow.mono.entity.Address;
import com.boulow.mono.enumeration.AddressType;
import com.boulow.mono.repository.AddressRepository;

@Service("address-service")
@Transactional
public class AddressServiceImpl implements AddressService {
	
	@Autowired
	private AddressRepository addressRepository;

	@Override
	public Optional<Address> findById(Long id) {
		return addressRepository.findById(id);
	}

	@Override
	public Iterable<Address> findByType(AddressType type) {
		return addressRepository.findByType(type);
	}

	@Override
	public Iterable<Address> findAll() {
		return addressRepository.findAll();
	}

	@Override
	public Address save(Address address) {
		return addressRepository.save(address);
	}

	@Override
	public void update(Address obsolete, Address latest) {
		obsolete.setCity(latest.getCity());
		obsolete.setStreet(latest.getStreet());
		obsolete.setSuite(latest.getSuite());
		obsolete.setProvince(latest.getProvince());
		obsolete.setType(latest.getType());
		obsolete.setLat(latest.getLat());
		obsolete.setLon(latest.getLon());
	}

}
