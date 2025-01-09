package com.certTrack.UserService.Controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	@DeleteMapping("/delete")
	public ResponseMessage deleteUser(@RequestParam Long id) {
		String message = userService.delete(id);
		return new ResponseMessage(message);
	}
}
