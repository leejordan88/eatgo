package kr.co.fastcampus.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import kr.co.fastcampus.domain.User;
import kr.co.fastcampus.domain.UserRepository;
class UserServiceTest {

	private UserService userService;
	
	@Mock
	private UserRepository userRepository;

	@BeforeEach
	public void setUp() {							
		MockitoAnnotations.initMocks(this);
		userService = new UserService(userRepository);
	}
	
	@Test
	public void getUsers() {
		List<User> mockUsers = new ArrayList<User>();
		mockUsers.add(User.builder().email("tester@example.com").name("tester").level(1L).build());
		given(userRepository.findAll()).willReturn(mockUsers);
		
		List<User> users = userService.getUsers();
		User user = users.get(0);
		assertThat(user.getName(), is("tester"));
	}
	
	@Test
	public void addUser() {
		String email = "tester@example.com";
		String name = "tester";
		Long level = 1L;
		
		User mockUser = User.builder()
				.email(email)
				.name(name)
				.build();
		
		given(userRepository.save(any())).willReturn(mockUser);
		
		User user =userService.addUser(email, name, level);
		assertThat(user.getName(), is(name));
	}
	
	@Test
	public void update() {
		Long id = 1004L;
		String email = "tester@example.com";
		String name = "superman";
		Long level = 100L;
		
		User mockUser = User.builder()
				.id(id)
				.email(email)
				.name(name)
				.level(level)
				.build();
		given(userRepository.findById(id)).willReturn(Optional.of(mockUser));
		given(userRepository.save(mockUser)).willReturn(mockUser);
		
		User user = userService.updateUser(id, email, "superman", 100L);
		verify(userRepository).findById(eq(id));
		assertThat(user.getName(), is("superman"));
		assertThat(user.isAdmin(), is(true));
	}
	
	@Test
	public void deactiveUser() {
		Long id = 1004L;
		
		User mockUser = User.builder()
				.id(id)
				.email("tester@example.com")
				.name("superman")
				.level(100L)
				.build();
		
		given(userRepository.findById(id)).willReturn(Optional.of(mockUser));
		mockUser.deActivate();
		given(userRepository.save(mockUser)).willReturn(mockUser);
		User user = userService.deactivateUser(id);
		
		verify(userRepository).findById(1004L);
		
		assertThat(user.isAdmin(), is(false));
		assertThat(user.isActive(), is(false));
	}
	

}









