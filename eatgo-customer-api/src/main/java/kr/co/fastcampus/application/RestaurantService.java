package kr.co.fastcampus.application;

import java.util.List;

import kr.co.fastcampus.domain.Restaurant;

public interface RestaurantService {
	public List<Restaurant> getRestaurants();

	public Restaurant getRestaurantById(Long id);

}
