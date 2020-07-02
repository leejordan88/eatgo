package kr.co.fastcampus.interfaces;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
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
import kr.co.fastcampus.exception.EmailNotExistedException;
import kr.co.fastcampus.exception.PasswordWrongException;

@WebMvcTest(SessionController.class)
@ContextConfiguration(classes={SessionController.class, SecurityConfig.class, SessionErrorAdvice.class})
class SessionControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private UserService userService;
	
	@Test
	@DisplayName("세션 생성 성공")
	public void createWithValidAttributes() throws Exception {
		String email = "tester@example.com";
		String password = "test";
		User mockUser = User.builder()
				.password("ACCESSTOKEN")
				.build();
		given(userService.authenticate(email, password)).willReturn(mockUser);
		
		mvc.perform(post("/session")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"email\":\"tester@example.com\",\"name\":\"Tester\",\"password\":\"test\"}"))
			.andExpect(status().isCreated())
			.andExpect(header().string("location", "/session"))
			.andExpect(content().string("{\"accessToken\":\"ACCESSTOKE\"}"));
		
		verify(userService).authenticate(eq(email), eq(password));
	}
	
	@Test
	@DisplayName("패스워드 불일치")
	public void createWithWrongPassword() throws Exception {
		
		given(userService.authenticate("tester@example.com", "x")).willThrow(PasswordWrongException.class);
		
		mvc.perform(post("/session")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"email\":\"tester@example.com\",\"name\":\"Tester\",\"password\":\"x\"}"))
			.andExpect(status().isBadRequest());
		
		verify(userService).authenticate(eq("tester@example.com"), eq("x"));
	}
	
	@Test
	@DisplayName("존재하는 이메일 없음")
	public void createWithNotExistedEmail() throws Exception {
		given(userService.authenticate("x@example.com", "test")).willThrow(EmailNotExistedException.class);
		
		mvc.perform(post("/session")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"email\":\"x@example.com\",\"name\":\"Tester\",\"password\":\"test\"}"))
			.andExpect(status().isBadRequest());
		
		verify(userService).authenticate(eq("x@example.com"), eq("test"));
	}
	
}
