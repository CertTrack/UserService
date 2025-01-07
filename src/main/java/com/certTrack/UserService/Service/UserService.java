package com.certTrack.UserService.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.certTrack.UserService.Entity.User;
import com.certTrack.UserService.Repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public Optional<User> findByEmail(String email) {
		System.out.println(email);
		return userRepository.findByEmail(email);
	}

	public User saveUser(String email, String password) {
		return userRepository
				.save(User.builder().email(email).password(passwordEncoder.encode(password)).role("ROLE_USER").build());
	}

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User findById(Long id) {
		return userRepository.findById(id).get();
	}

}