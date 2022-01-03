package com.boulow.mono.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boulow.mono.entity.Address;
import com.boulow.mono.entity.User;
import com.boulow.mono.entity.dto.UserUpdateDTO;
import com.boulow.mono.repository.UserRepository;

@Service("user-service")
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	
	@Autowired
	private AddressService addressService;
	
	@Autowired
	private FileStorageService fileStorageService;

	@Override
	public Optional<User> findById(Long id) {
		return userRepository.findById(id);
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public Optional<User> findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public Optional<User> findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public List<User> findByFirstNameContainingIgnoreCase(String firstname) {
		return userRepository.findByFirstNameContainingIgnoreCase(firstname);
	}

	@Override
	public List<User> findByLastNameContainingIgnoreCase(String lastname) {
		return userRepository.findByLastNameContainingIgnoreCase(lastname);
	}

	@Override
	public List<User> findByIsActiveTrue() {
		return userRepository.findByIsActiveTrue();
	}

	@Override
	public Boolean existsByUsername(String username) {
		return userRepository.existsByUsername(username);
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public void delete(Long id) {
		userRepository.deleteById(id);
	}

	@Override
	public User update(User fromUser, UserUpdateDTO toUser) {
		fromUser.setBio(toUser.getBio());
		fromUser.setDob(toUser.getDob());
		fromUser.setFirstName(toUser.getFirstName());
		fromUser.setLastName(toUser.getLastName());
		fromUser.setGender(toUser.getGender());
		fromUser.setPhone(toUser.getPhone());
		fromUser.setLangKey(toUser.getLangKey());
		if(toUser.getAddress() != null) {
			if(fromUser.getAddress() == null) {
				Address newAddress = new Address();
				addressService.update(newAddress, toUser.getAddress());
				fromUser.setAddress(newAddress);
			} else {
				addressService.update(fromUser.getAddress(), toUser.getAddress());
			}
		}
		if(toUser.getAvatar() != null) {
			fromUser.setImageUrl(fileStorageService.saveFiles(List.of(toUser.getAvatar()), 0).get(0));
		}
		return userRepository.save(fromUser);
	}

}
