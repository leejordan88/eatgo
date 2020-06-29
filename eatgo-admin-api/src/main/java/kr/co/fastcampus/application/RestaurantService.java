package kr.co.fastcampus.application;

import java.util.List;

import kr.co.fastcampus.domain.Restaurant;

public interface RestaurantService {
	public List<Restaurant> getRestaurants();

	public Restaurant getRestaurantById(Long id);

	public Restaurant addRestaurant(Restaurant restaurant);

	public Object updateRestaurant(long id, String name, String address);
}
