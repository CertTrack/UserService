package com.certTrack.UserService.Controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.certTrack.UserService.Security.UserPrincipal;
import com.certTrack.UserService.Service.UserService;
import com.certTrack.UserService.model.LoginRequest;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
	private final UserService userService;
	
	@PostMapping("/register")
	public String register(@RequestBody @Validated LoginRequest loginRequest) {
		userService.saveUser(loginRequest.getEmail(), loginRequest.getPassword());
		return "user succesfully register";
	}
	@GetMapping("/")
	public String start() {
		return "Bla Bla bLa";
	}
	@GetMapping("/secured")
	public String authTest(@AuthenticationPrincipal UserPrincipal principal) { 
		return "secuder ID: "+principal.getUserId()+principal.getEmail();
	}
	
	@GetMapping("/admin")
	public String adminendpoint(@AuthenticationPrincipal UserPrincipal principal) {
		return "you are an admin";
	}
}