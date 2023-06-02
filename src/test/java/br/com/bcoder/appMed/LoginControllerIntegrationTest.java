package br.com.bcoder.appMed;

import br.com.bcoder.appMed.model.UserModel;
import br.com.bcoder.appMed.repository.UserRepository;
import br.com.bcoder.appMed.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LoginControllerIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserService userService;


	@Test
	@Transactional
	@DisplayName("Should create a new user")
	public void registerUserTest() throws Exception {
		mockMvc.perform(
						post("/register")
								.contentType(MediaType.APPLICATION_JSON)
								.content("{ \"email\": \"user2@user.com\", \"displayName\": \"Anna Flower\", \"password\": \"12345678\" }")
				).andExpect(status().isCreated())
				.andExpect(content().string("User Anna Flower created."));
	}

	@Test
	@Transactional
	@DisplayName("Should return that user exists")
	public void registerExistsUserTest() throws Exception {
		createUser();
		mockMvc.perform(
						post("/register")
								.contentType(MediaType.APPLICATION_JSON)
								.content("{ \"email\": \"user2@user.com\", \"displayName\": \"Anna Flower\", \"password\": \"12345678\" }")
				).andExpect(status().isOk())
				.andExpect(content().string("User Anna Flower exists."));
	}

	@Test
	@Transactional
	@DisplayName("Should return unauthorized when password doesn't match")
	public void registerExistsWrongPassUserTest() throws Exception {
		createUser();
		mockMvc.perform(
						post("/register")
								.contentType(MediaType.APPLICATION_JSON)
								.content("{ \"email\": \"user2@user.com\", \"displayName\": \"Anna Flower\", \"password\": \"123456789\" }")
				).andExpect(status().isUnauthorized())
				.andExpect(content().string("User Anna Flower exists on our database, but the password doesn't match."));
	}

	private void createUser() {
		UserModel user = new UserModel();
		user.setEmail("user2@user.com");
		user.setDisplayName("Anna Flower");
		user.setPassword("12345678");
		userService.registerUser(user);
	}
}
