package kr.co.fastcampus.domain;

import org.junit.jupiter.api.Test;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

class UserTest {

	@Test
	public void create() {
		User user = User.builder()
				.email("tester@example.com")
				.name("테스터")
				.level(100L)
				.build()
				;
		
		assertThat(user.getName(), is("테스터"));
		assertThat(user.isAdmin(), is(true));
	}
}
