package com.certTrack.UserService.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private JWTAuthenticationFilter filter;
	
	@Autowired
	private CustomUserDetailService customUserDetailService;
	
	@Bean
	public SecurityFilterChain applicationsecurity(HttpSecurity http) throws Exception{
		http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
		http
			.cors(CorsConfigurer::disable)
			.csrf(AbstractHttpConfigurer::disable)
			.sessionManagement(sessionManagement->sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.formLogin(formLogin->formLogin.disable())		//прибираємо форму для логіну
			.securityMatcher("/**")			//за якими посиланнями працює секюріті
			.authorizeHttpRequests(			//дозволяємо доступ всім??
					(registry) -> registry
						.requestMatchers("/").permitAll()
						.requestMatchers("/auth/login").permitAll()
						.requestMatchers("/admin/**").hasRole("ADMIN")
						.anyRequest().authenticated()
					);
		return http.build();
		
	}
	
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception{
		var builder = http.getSharedObject(AuthenticationManagerBuilder.class);
		
		builder.userDetailsService(customUserDetailService)
		.passwordEncoder(encoder());
		
		return builder.build();
	}
}