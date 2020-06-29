package kr.co.fastcampus.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ReviewRepository extends CrudRepository<Review, Long> {

	List<Review> findAllByRestaurantId(Long restaurantId);
	
	@SuppressWarnings("unchecked")
	Review save(Review review);
}
