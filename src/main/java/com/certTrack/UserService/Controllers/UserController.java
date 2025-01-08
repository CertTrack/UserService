package com.certTrack.UserService.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.certTrack.UserService.Entity.User;
import com.certTrack.UserService.Security.UserPrincipal;
import com.certTrack.UserService.Service.UserService;
import com.certTrack.UserService.model.LoginRequest;
import com.certTrack.UserService.model.ResponseMessage;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
	private final UserService userService;
	
	@GetMapping("/")
	public String start() {
		return "USER SERVICE";
	}
	
	@GetMapping("/user")
	public User getUser(@RequestParam Long id) {
		return userService.findById(id);
	}

	@PostMapping("/register")
	public ResponseEntity<ResponseMessage> register(@RequestBody @Validated LoginRequest loginRequest) {
		String message = userService.saveUser(loginRequest.getEmail(), loginRequest.getPassword());
	    return ResponseEntity.ok(new ResponseMessage(message));	}
	
	//endpoint for test is user authorized
	@GetMapping("/secured")
	public String authTest(@AuthenticationPrincipal UserPrincipal principal) { 
		return "secuder ID: "+principal.getUserId()+" " +principal.getEmail();
	}
}