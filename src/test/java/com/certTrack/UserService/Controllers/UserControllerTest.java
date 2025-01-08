package com.certTrack.UserService.Controllers;

import static org.hamcrest.Matchers.containsStringIgnoringCase;
import static org.hamcrest.Matchers.matchesPattern;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.certTrack.UserService.model.LoginRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class UserControllerTest {

	@Autowired
	private MockMvc api;
	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void anyOneCanViewPublicEndpoint() throws Exception {
		api.perform(get("/users/")).andExpect(status().isOk())
				.andExpect(content().string(containsStringIgnoringCase("USER SERVICE")));
	}

	@Test
	void anyOneCanRegister() throws Exception {
		LoginRequest loginRequest = new LoginRequest("test@example.com", "password123");
		String requestJson = objectMapper.writeValueAsString(loginRequest);

		api.perform(post("/users/register").contentType(MediaType.APPLICATION_JSON).content(requestJson))
				.andExpect(status().isOk()).andExpect(jsonPath("$.message").value("This user is already present!"));
	}

	@Test
	void notLogedInshouldnotseesecuredEndpoint() throws Exception {
		api.perform(get("/secured")).andExpect(status().is4xxClientError());
	}

	@Test
	@WithMockUser
	void logedInshouldSeeSecuredEndpoint() throws Exception {
		api.perform(get("/users/secured")).andExpect(status().isOk())
				.andExpect(content().string(containsStringIgnoringCase("secuder ID: ")));
	}

	@Test
	@WithMockUser
	void loged_In_should_See_His_ID_In_Secured_Endpoint() throws Exception {
		api.perform(get("/users/secured")).andExpect(status().isOk())
				.andExpect(content().string(containsStringIgnoringCase("ID")));
	}

	@Test
	void while_LoggedIn_shouldReceiveJWT() throws Exception {
		LoginRequest loginRequest = new LoginRequest("test@example.com", "password123");
		String requestJson = objectMapper.writeValueAsString(loginRequest);

		api.perform(post("/auth/login").contentType(MediaType.APPLICATION_JSON).content(requestJson))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.accestoken").value(matchesPattern("^eyJhbGciOiJIUzI1N.*")));
	}

	@WithMockUser
	@Test
	void Authorized_User_Can_See_Information_About_User_By_Id() throws Exception {
		api.perform(get("/users/user?id=1")).andExpect(status().isOk()).andExpect(jsonPath("$.id").value(1))
				.andExpect(jsonPath("$.email").value("dimatrofimov515@gmail.com"))
				.andExpect(jsonPath("$.role").value("ROLE_ADMIN"));
	}

	@Test
	void Not_Authorized_User_CanNOT_See_Information_About_User_By_Id() throws Exception {
		api.perform(get("/users/user?id=1"))
			.andExpect(status()
			.is4xxClientError());
	}
}
