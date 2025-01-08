package com.certTrack.UserService.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper; 
import org.junit.jupiter.api.Test; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc; 
import org.springframework.boot.test.context.SpringBootTest; 
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType; 
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*; 
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*; 
import static org.hamcrest.Matchers.*;

import com.certTrack.UserService.Service.AuthService;
import com.certTrack.UserService.model.LoginRequest;
import com.certTrack.UserService.model.LoginResponse;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class UserControllerTest {

	@Autowired
	private MockMvc api;
    @Autowired
    private ObjectMapper objectMapper;
	
	@Test
	void anyOneCanViewPublicEndpoint() throws Exception {
		api.perform(get("/users/")).
		andExpect(status().isOk())
		.andExpect(content().string(containsStringIgnoringCase("USER SERVICE")));
	}
	
	@Test
	void anyOneCanRegister() throws Exception {
        LoginRequest loginRequest = new LoginRequest("test@example.com", "password123");
        String requestJson = objectMapper.writeValueAsString(loginRequest);
		
        api.perform(post("/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("This user is already present!"));
    }
	
	@Test
	void notLogedInshouldnotseesecuredEndpoint() throws Exception {
		api.perform(get("/secured")).
		andExpect(status().is4xxClientError());
	}
	
	
	@Test
	@WithMockUser
	void logedInshouldSeeSecuredEndpoint() throws Exception {
		api.perform(get("/users/secured")).
		andExpect(status().isOk())
		.andExpect(content().string(containsStringIgnoringCase("secuder ID: ")));
	}
	
	@Test
	@WithMockUser
	void loged_In_should_See_His_ID_In_Secured_Endpoint() throws Exception {
		api.perform(get("/users/secured")).
		andExpect(status().isOk())
		.andExpect(content().string(containsStringIgnoringCase("ID")));
	}
	
	
	@Test
	void whileLoggedIn_shouldReceiveJWT() throws Exception {
	    LoginRequest loginRequest = new LoginRequest("test@example.com", "password123");
	    String requestJson = objectMapper.writeValueAsString(loginRequest);

	    api.perform(post("/auth/login")
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(requestJson))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$.accestoken").value(matchesPattern("^eyJhbGciOiJIUzI1N.*")));
	}

	
	
//	private final AuthService authService;
//	
//	@PostMapping("/auth/login")
//	public LoginResponse login(@RequestBody @Validated LoginRequest loginRequest) {
//		return authService.attemptlogin(loginRequest.getEmail(), loginRequest.getPassword());
//	}
}
