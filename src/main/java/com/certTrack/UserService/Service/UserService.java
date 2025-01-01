package com.certTrack.UserService.Service;

import org.springframework.stereotype.Service;

import com.certTrack.UserService.Entity.Role;
import com.certTrack.UserService.Entity.User;
import com.certTrack.UserService.Repository.UserRepository;

@Service
public class UserService {
	
	private final UserRepository repo;
	
	public UserService(UserRepository repo) {
		this.repo = repo;
	}
	
	public void saveUser(User user) {
		user.setRole(Role.STUDENT);
		repo.save(user);
	} 
}
