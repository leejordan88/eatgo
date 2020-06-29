package kr.co.fastcampus.interfaces;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import kr.co.fastcampus.application.RestaurantService;
import kr.co.fastcampus.domain.Restaurant;

@CrossOrigin
@RestController
public class RestaurantController {

	@Autowired
	private RestaurantService restaurantService;

	@GetMapping("/restaurants")
	public List<Restaurant> list() {
		return restaurantService.getRestaurants();
	}

	@GetMapping("/restaurant/{id}")
	public Restaurant detail(@PathVariable("id") Long id) {
		return restaurantService.getRestaurantById(id);
	}


}