package com.certTrack.UserService.Controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.certTrack.UserService.Entity.User;
import com.certTrack.UserService.Service.UserService;
import com.certTrack.UserService.model.LoginRequest;

@RestController
@RequestMapping("/users")
public class UserController {
	
	
	private final UserService service;
	
	public UserController(UserService service) {
		this.service = service;
	}
	
	@RequestMapping("/login")
	public ResponseEntity<User> login(@RequestBody LoginRequest loginRequest) {
		return ResponseEntity.ok(service.saveUser(loginRequest.getEmail(), loginRequest.getPassword()));
	}
//	Логін користувача
//
//    Метод: POST
//    Шлях: /users/login
//    Опис: Авторизація користувача.
//    Тіло запиту:
//
//{
//  "email": "string",
//  "password": "string"
//}
//
//Відповідь:
//
//{
//  "token": "jwt_token",
//  "message": "Login successful"
//}
	/*
	 * also delete
	 */

	
	@GetMapping("/all")
	public List<User> findAllUsers() {
		return service.findAll();
	}
	@GetMapping("/")
	public User findById(@RequestParam int id) {
		return service.findById(id);
	}
}