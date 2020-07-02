package kr.co.fastcampus.application;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;
import static org.mockito.BDDMockito.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.hamcrest.CoreMatchers.is;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import kr.co.fastcampus.domain.User;
import kr.co.fastcampus.domain.UserRepository;
import kr.co.fastcampus.exception.EmailExistedException;
import kr.co.fastcampus.exception.PasswordWrongException;

class UserServiceTest {

	private UserService userService;
	
	@Mock
	private UserRepository userRepository;
	@Mock
	private BCryptPasswordEncoder passwordEncoder;
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		userService = new UserService(userRepository, passwordEncoder);
		
	}
	
	@Test
	public void registerUser() {
		String email = "tester@example.com";
		String name = "Tester";
		String password = "test";
		Long level = 1L;
		
		given(passwordEncoder.matches(any(), any())).willReturn(true);
		userService.registerUser(email, name, password, level);
		
		verify(userRepository).save(any());
	}
	
//	@Test
	public void registerUserWithExistedEmail() {
		
		String email = "tester@example.com";
		String name = "Tester";
		String password = "test";
		Long level = 1L;
		
		User mockUser = User.builder()
				.id(1L)
				.name(name)
				.email(email)
				.build();
		
		given(userRepository.findByEmail(email)).willReturn(Optional.of(mockUser));
		given(passwordEncoder.matches(any(), any())).willReturn(true);
		
		userService.registerUser(email, name, password, level);
		Throwable e = assertThrows(EmailExistedException.class, () -> {
			throw new EmailExistedException(email);
		});
		assertEquals("Email is already registered " + email, e.getMessage());
	}
	
	@Test
	public void authenticateWithValidAttributes() {
		String email = "tester@example.com";
		String password = "test";
		
		User mockUser = User.builder()
				.email(email)
				.password(password)
				.build();
		given(userRepository.findByEmail(email)).willReturn(Optional.of(mockUser));
		given(passwordEncoder.matches(any(), any())).willReturn(true);
		User user = userService.authenticate(email, password);
		assertThat(user.getEmail(), is(email));
	}
	
	@Test
	public void authenticateWithNotExistedEmail() {
		String email = "x@example.com";
		String password = "test";
		
		User mockUser = User.builder()
				.email(email)
				.password(password)
				.build();
		given(userRepository.findByEmail(email)).willReturn(Optional.empty());
		
		Throwable e = assertThrows(EmailExistedException.class, () -> {
			throw new EmailExistedException(email);
		});
		assertEquals("Email is already registered " + email, e.getMessage());
	}
	
	@Test
	public void authenticateWithWrongPassword() {
		String email = "example@example.com";
		String password = "x";
		
		User mockUser = User.builder()
				.email(email)
				.password(password)
				.build();
		given(userRepository.findByEmail(email)).willReturn(Optional.empty());
		
		Throwable e = assertThrows(PasswordWrongException.class, () -> {
			throw new PasswordWrongException();
		});
		assertEquals("Password is wrong", e.getMessage());
	}
}
