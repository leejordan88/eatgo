package kr.co.fastcampus.application;

import java.util.ArrayList;
import java.util.List;
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
	

}









