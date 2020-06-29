package kr.co.fastcampus.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.fastcampus.domain.MenuItemRepository;
import kr.co.fastcampus.domain.Restaurant;
import kr.co.fastcampus.domain.RestaurantNotFoundException;
import kr.co.fastcampus.domain.RestaurantRepository;
import kr.co.fastcampus.domain.Review;
import kr.co.fastcampus.domain.ReviewRepository;

@Service
public class RestaurantServiceImpl implements RestaurantService {

	private RestaurantRepository restaurantRepository;
	private MenuItemRepository menuItemRepository;
	private ReviewRepository reviewRepository;

	@Autowired
	public RestaurantServiceImpl(RestaurantRepository restaurantRepository, MenuItemRepository menuItemRepository,
			ReviewRepository reviewRepository) {
		this.restaurantRepository = restaurantRepository;
		this.menuItemRepository = menuItemRepository;
		this.reviewRepository = reviewRepository;
	}

	@Override
	public List<Restaurant> getRestaurants() {
		return restaurantRepository.findAll();
	}

	@Override
	public Restaurant getRestaurantById(Long id) {
		Restaurant restaurant = restaurantRepository.findById(id)
				.orElseThrow(() -> new RestaurantNotFoundException(id));
		restaurant.setMenuItems(menuItemRepository.findAllByRestaurantId(id));
		List<Review> reviews = reviewRepository.findAllByRestaurantId(id);
		restaurant.setReviews(reviews);
		return restaurant;
	}

	@Override
	public Restaurant addRestaurant(Restaurant restaurant) {
		return restaurantRepository.save(restaurant);
	}

	@Override
	@Transactional
	public Restaurant updateRestaurant(long id, String name, String address) {
		Restaurant restaurant = getRestaurantById(id);
		restaurant.updateInfomation(name, address);
		return restaurant;
	}

}
