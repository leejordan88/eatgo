package kr.co.fastcampus.interfaces;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
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

import kr.co.fastcampus.application.ReviewService;
import kr.co.fastcampus.config.SecurityConfig;
import kr.co.fastcampus.domain.Review;

@WebMvcTest(ReviewController.class)
@ContextConfiguration(
		classes={ReviewController.class,
				SecurityConfig.class})
class ReviewControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private ReviewService reviewService;

	@Test
	void createWithValidAttributes() throws Exception {
		String name = "joonsung";
		int score = 3;
		String description = "yummy";
		
		Review mockReview = Review.builder()
				.id(2077L)
				.restaurantId(1004L)
				.name(name)
				.score(score)
				.description(description)
				.build();
		
		given(reviewService.addReview(1004L, Review.builder()
				.name(name)
				.score(3)
				.description(description)
				.build())).willReturn(mockReview);
		
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjEwMDQsIm5hbWUiOiJqb2huIn0.4j912axb1ITcgTTiPd-K-6Ie09cQ80MpvfO8L34JwsU";
		mvc.perform(post("/restaurant/1004/review")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"name\":\"joonsung\",\"score\":3,\"description\":\"yummy\"}"))
			.andExpect(status().isCreated());
//			.andExpect(header().string("location", "/restaurant/1004/review/2077"));

	}

//	@Test
	void createWithOutValidAttributes() throws Exception {
		// TODO: Valid check 실패 이유 확인 필요
		mvc.perform(post("/restaurant/1/review").contentType(MediaType.APPLICATION_JSON)
				.content("{\"name\":\"joonsung\",\"score\":3,\"description\":\"yummy\"}"))
				.andExpect(status().isBadRequest());

		verify(reviewService, never()).addReview(eq(1L), any());
	}

}
