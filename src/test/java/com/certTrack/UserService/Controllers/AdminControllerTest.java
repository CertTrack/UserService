package com.certTrack.UserService.Controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class AdminControllerTest {

	@Autowired
	private MockMvc api;

	@Test
	void anyOneCanNotViewAdminEndpoint() throws Exception {
		api.perform(get("/admin")).
		andExpect(status().is4xxClientError());
	}
	
	@WithMockUser
	@Test
	void SimpleUserCanNotViewAdminEndpoint() throws Exception {
		api.perform(get("/admin")).
		andExpect(status().is4xxClientError());
	}
	
	@WithMockUser(auth = "ROLE_ADMIN")
	@Test
	void AdminCanViewAdminEndpoint() throws Exception {
		api.perform(get("/admin/all")).
		andExpect(status().isOk());
	}
}
