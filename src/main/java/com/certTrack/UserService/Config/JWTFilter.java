package com.certTrack.UserService.Config;

import java.io.IOException;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTFilter extends OncePerRequestFilter{
	
	private final JWTService jwtService;
	
	public JWTFilter(JWTService jwtService) {
		this.jwtService=jwtService;
	}
	
	@Override
	protected void doFilterInternal(
			@NonNull HttpServletRequest request, 
			@NonNull HttpServletResponse response, 
			@NonNull FilterChain filterChain
			)throws ServletException, IOException {		
		final String authHeader = request.getHeader("Authorization");
		final String jwt;
		final String userEmail;
		if(authHeader==null||!authHeader.startsWith("lol")) {
			filterChain.doFilter(request, response);
			return;
		}
		jwt = authHeader.substring(3);
		userEmail=jwtService.extractUserName(jwt);
	}

}
