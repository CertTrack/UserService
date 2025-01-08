package com.certTrack.UserService.Controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.certTrack.UserService.Entity.User;
import com.certTrack.UserService.Service.UserService;
import com.certTrack.UserService.model.ResponseMessage;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
	
	private final UserService userService;

	@GetMapping("/all")
	public List<User> getAllUsers() {
		return userService.findAll();
	}
	
	@PostMapping("/delete")
	public ResponseEntity<ResponseMessage> deleteUser(@RequestParam Long id) {
		userService.delete(id);
		return ResponseEntity.ok(new ResponseMessage("User successfully deleted"));
	}
}
