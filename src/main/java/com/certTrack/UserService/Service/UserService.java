package com.certTrack.UserService.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.certTrack.UserService.Entity.User;
import com.certTrack.UserService.Repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {
	
	private final UserRepository repo;
	private final PasswordEncoder passwordEncoder;
	
	
	public User saveUser(String email, String password) {
		return repo.save(User
				.builder()
				.email(email)
				.password(passwordEncoder.encode(password))
				.role("STUDENT").build());
	}
	
	
	
	
	public Optional<User> findByEmail(String email) {
		return repo.findByEmail(email);
	} 
	
	
//	private static final String USER_EMAIL = "user@test.com";
//	private static final String ADMIN_EMAIL = "admin@test.com";
//
//	public Optional<User> findByEmail(String email) {
//		if (email.equalsIgnoreCase(USER_EMAIL)) {
//			var user = new User();
//			user.setId(1L);
//			user.setEmail(USER_EMAIL);
//			user.setPassword("$2a$12$jYa3p54iDfAdKsBZD9J8Au/vcIk0diIQwnbGuPxZtrr4fJFlIi1jy");
//			user.setRole("ROLE_USER");
//			user.setExtraInfo("my nice user");
//			return Optional.of(user);
//		} else if (email.equalsIgnoreCase(ADMIN_EMAIL)) {
//			var user = new User();
//			user.setId(99L);
//			user.setEmail(ADMIN_EMAIL);
//			user.setPassword("$2a$12$jYa3p54iDfAdKsBZD9J8Au/vcIk0diIQwnbGuPxZtrr4fJFlIi1jy");
//			user.setRole("ROLE_ADMIN");
//			user.setExtraInfo("my nice admin");
//			return Optional.of(user);
//		} else {
//			return Optional.empty();
//		}
//	}
	
	
	
	
	
	
    
	public List<User> findAll() {
		return repo.findAll();
	}

	public User findById(int id) {
		return repo.findById(id).get();
	}
}
