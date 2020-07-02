package kr.co.fastcampus.interfaces;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import kr.co.fastcampus.application.UserService;
import kr.co.fastcampus.domain.User;

@WebMvcTest(UserController.class)
@ContextConfiguration(classes = {UserController.class})
class UserControllerTest {

	@Autowired
	MockMvc mvc;
	
	@MockBean
	private UserService userService;
	
	@Test
	public void list() throws Exception {
		List<User> users = new ArrayList<User>();
		users.add(User.builder().email("tester@example.com").name("tester").level(1L).build());
		given(userService.getUsers()).willReturn(users);
		
		mvc.perform(get("/users"))
			.andExpect(status().isOk())
			.andExpect(content().string(containsString("tester")));
	}
	
	@Test
	public void create() throws Exception {
		String email = "tester@example.com";
		String name = "tester";
		Long level = 1L;
		
		User user = User.builder()
				.email(email)
				.name(name)
				.build();
		given(userService.addUser(email, name, level)).willReturn(user);
		
		mvc.perform(post("/user")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"email\":\"tester@example.com\",\"name\":\"tester\",\"level\":1}"))
			.andExpect(status().isCreated());
		
		
		verify(userService).addUser(email, name, level);
	}
	
	@Test
	public void update() throws Exception {
		Long id = 1004L;
		String email = "tester@example.com";
		String name = "tester";
		Long level = 100L;
		
		mvc.perform(patch("/user/1004")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"email\":\"tester@example.com\",\"name\":\"tester\",\"level\":100}"))
			.andExpect(status().isOk());
		verify(userService).updateUser(eq(id), eq(email), eq(name), eq(level));
	}

	@Test
	public void deactivate() throws Exception {
		mvc.perform(delete("/user/1004"))
			.andExpect(status().isOk());
		
		verify(userService).deactivateUser(1004L);
		
		
	}
	
}
