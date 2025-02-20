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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
	private final UserService userService;
	
	
	@Operation(summary = "test endpoint", security = @SecurityRequirement(name = "basicScheme"), responses = {
	        @ApiResponse(description = "successful response", responseCode = "200")
	    })
	@GetMapping("/")
	public String start() {
		return "USER SERVICE";
	}
	
	@GetMapping("/user")
	public User getUser(@RequestParam Long id) {
		return userService.findById(id);
	}

	@PostMapping("/register")
	public ResponseEntity<ResponseMessage> register(@RequestParam String name, 
													@RequestParam String email,
													@RequestParam String password) {
		String message = userService.saveUser(name, email, password);
	    return ResponseEntity.ok(new ResponseMessage(message));	}
	
	//endpoint for test is user authorized
	@GetMapping("/secured")
	public String authTest(@AuthenticationPrincipal UserPrincipal principal) { 
		return "secuder ID: "+principal.getUserId()+" " +principal.getEmail();
	}
}