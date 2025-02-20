package com.certTrack.UserService.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.certTrack.UserService.Entity.User;
import com.certTrack.UserService.Repository.UserRepository;
import com.certTrack.UserService.model.ResponseMessage;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public Optional<User> findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public String saveUser(String name, String email, String password) {
		if(userRepository.findByEmail(email).isPresent()) {
			return "This user is already present!";
		}
		userRepository
				.save(User.builder().name(name).email(email).password(passwordEncoder.encode(password)).role("ROLE_USER").build());
		return "user successfully registered";
	}

	public List<User> findAll() {
		return userRepository.findAll();
	}

	public User findById(Long id) {
		return userRepository.findById(id).get();
	}

	public String  delete(Long id) {
		if(userRepository.findById(id).isPresent()) {
			userRepository.deleteById(id);
			return "user successfully deleted";
		}
		return "no user by this id";
	}

	public ResponseMessage setRole(Long userId, String role) {
		User user = findById(userId);
		user.setRole(role);
		userRepository.save(user);
		return new ResponseMessage("user role successfully updated");
	}

}