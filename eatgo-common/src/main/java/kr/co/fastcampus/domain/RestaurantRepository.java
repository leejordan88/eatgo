package kr.co.fastcampus.domain;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface RestaurantRepository extends CrudRepository<Restaurant, Long> {

	List<Restaurant> findAll();

	List<Restaurant> findAllByAddressContainingAndCategoryId(String region, Long categoryId);
	
	Optional<Restaurant> findById(Long id);

	@SuppressWarnings("unchecked")
	Restaurant save(Restaurant restaurant);


}