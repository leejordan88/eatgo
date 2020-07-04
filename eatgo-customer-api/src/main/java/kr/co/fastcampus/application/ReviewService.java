package kr.co.fastcampus.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.fastcampus.domain.Review;
import kr.co.fastcampus.domain.ReviewRepository;

@Service
public class ReviewService {

	@Autowired
	ReviewRepository reviewRepository;

	public ReviewService(ReviewRepository reviewRepository) {
		this.reviewRepository = reviewRepository;
	}

	public Review addReview(Long restaurantId, Review review) {
		review.setRestaurantId(restaurantId);
		return reviewRepository.save(review);
	}

}
