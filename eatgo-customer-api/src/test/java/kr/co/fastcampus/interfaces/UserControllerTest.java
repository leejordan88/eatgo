package kr.co.fastcampus.interfaces;

//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
//import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import kr.co.fastcampus.application.UserService;
import kr.co.fastcampus.config.SecurityConfig;
import kr.co.fastcampus.domain.User;

@WebMvcTest(UserController.class)
@ContextConfiguration(classes={UserController.class, SecurityConfig.class})
class UserControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private UserService userService;

	@Test
	public void create() throws Exception {
		
		Long id = 1004L;
		String email = "tester@example.com";
		String name = "Tester";
		Long level = 1L;
		String password = "test";
		
		User mockUser = User.builder()
				.id(id)
				.email(email)
				.name(name)
				.level(level)
				.password(password)
				.build();
		
		given(userService.registerUser("tester@example.com", "Tester", "test", 1L)).willReturn(mockUser);
		
		mvc.perform(post("/user")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"email\":\"tester@example.com\",\"name\":\"Tester\",\"password\":\"test\"}"))
				.andExpect(status().isCreated())
				.andExpect(header().string("location", "/user/1004"));
		
		verify(userService).registerUser(eq(email), eq(name), eq(password), eq(level));

	}

}
