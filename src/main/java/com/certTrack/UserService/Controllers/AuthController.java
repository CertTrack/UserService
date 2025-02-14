package com.certTrack.UserService.Controllers;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.certTrack.UserService.Service.AuthService;
import com.certTrack.UserService.model.LoginRequest;
import com.certTrack.UserService.model.LoginResponse;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
public class AuthController {
	
	private final AuthService authService;
	
	@PostMapping("/auth/login")
	public LoginResponse login(@RequestBody @Validated LoginRequest loginRequest) {
		return authService.attemptlogin(loginRequest.getEmail(), loginRequest.getPassword());
	}
}
	 