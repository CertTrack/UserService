package com.certTrack.UserService.Controllers;

import static org.hamcrest.Matchers.containsStringIgnoringCase;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class UserControllerTest {

	@Autowired
	private MockMvc api;
	
	@Test
	void anyOneCanViewPublicEndpoint() throws Exception {
		api.perform(get("/users/")).
		andExpect(status().isOk())
		.andExpect(content().string(containsStringIgnoringCase("test endpoint")));
	}
	
	@Test
	void notLogedInshouldnotseesecuredEndpoint() throws Exception {
		api.perform(get("/secured")).
		andExpect(status().is4xxClientError());
	}
	
	
	///////////////
	@Test
	@WithMockUser
	void logedInshouldSeeSecuredEndpoint() throws Exception {
		api.perform(get("/users/secured")).
		andExpect(status().isOk())
		.andExpect(content().string(containsStringIgnoringCase("secuder ID: ")));
	}
	
	///////////////
	@Test
	@WithMockUser
	void loged_In_should_See_His_ID_In_Secured_Endpoint() throws Exception {
		api.perform(get("/users/secured")).
		andExpect(status().isOk())
		.andExpect(content().string(containsStringIgnoringCase("ID")));
	}
}
