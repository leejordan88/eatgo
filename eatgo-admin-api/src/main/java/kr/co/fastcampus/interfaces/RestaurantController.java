package kr.co.fastcampus.interfaces;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import kr.co.fastcampus.application.MenuItemService;
import kr.co.fastcampus.application.RestaurantService;
import kr.co.fastcampus.domain.Restaurant;

@CrossOrigin
@RestController
public class RestaurantController {

	@Autowired
	private RestaurantService restaurantService;

	@Autowired
	private MenuItemService menuItemService;

	@GetMapping("/restaurants")
	public List<Restaurant> list() {
		return restaurantService.getRestaurants();
	}

	@GetMapping("/restaurant/{id}")
	public Restaurant detail(@PathVariable("id") Long id) {
		return restaurantService.getRestaurantById(id);
	}

	@PostMapping("/restaurant")
	public ResponseEntity<?> create(@RequestBody Restaurant restaurant) throws URISyntaxException {
		URI location = null;
		try {
			restaurant = restaurantService.addRestaurant(restaurant);
			if (restaurant.getMenuItems() != null) {
				menuItemService.bulkUpdate(restaurant.getId(), restaurant.getMenuItems());
			}
			location = new URI("/restaurant/" + restaurant.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ResponseEntity.created(location).body(restaurant);
	}

	@PatchMapping("/restaurant/{id}")
	public String update(@PathVariable Long id, @RequestBody Restaurant restaurant) {
		restaurantService.updateRestaurant(id, restaurant.getName(), restaurant.getAddress());
		return "{}";
	}
}
