package kr.co.fastcampus.application;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import kr.co.fastcampus.domain.Review;
import kr.co.fastcampus.domain.ReviewRepository;

class ReviewServiceTest {

	private ReviewService reviewService;

	@Mock
	private ReviewRepository reviewRepository;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		reviewService = new ReviewService(reviewRepository);
	}

	@Test
	public void addReview() {

		Review review = Review.builder().name("joonsung").score(3).description("yummy").build();

		reviewService.addReview(1L, review);

		verify(reviewRepository).save(any());
	}

}
