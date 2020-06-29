package kr.co.fastcampus.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface MenuItemRepository extends CrudRepository<MenuItem, Long> {

	List<MenuItem> findAllByRestaurantId(Long restaurantId);

	@SuppressWarnings("unchecked")
	MenuItem save(MenuItem menuItem);
	
	void deleteById(Long id);

	List<MenuItem> findByRestaurantId(Long restaurantId);

}
