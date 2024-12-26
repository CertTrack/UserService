package com.certTrack.UserService.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.certTrack.UserService.Entity.Role;
import com.certTrack.UserService.Entity.User;
import com.certTrack.UserService.Repository.UserRepository;
import com.certTrack.UserService.dto.ResponseMessage;

@RestController
@RequestMapping("/users")
public class MController {
	
	@Autowired
	UserRepository repo;
	
	@PostMapping("/register")
	public ResponseEntity<ResponseMessage> postuser(@RequestBody User user) {
		user.setRole(Role.STUDENT);
		repo.save(user);
		return ResponseEntity.ok(new ResponseMessage("User registered successfully."));
	}
	
	@PostMapping("/login")
	public ResponseEntity<User> login(@RequestBody User user) {
		repo.save(user);
		return ResponseEntity.ok(user);
	}
}
