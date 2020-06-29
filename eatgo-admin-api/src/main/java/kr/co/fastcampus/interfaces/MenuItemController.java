package kr.co.fastcampus.interfaces;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import kr.co.fastcampus.application.MenuItemService;
import kr.co.fastcampus.domain.MenuItem;

@RestController
public class MenuItemController {

	@Autowired
	private MenuItemService menuItemService;

	@PatchMapping("/restaurant/{restaurantId}/menuitems")
	public String bulkUpdate(@PathVariable("restaurantId") Long restaurantId, @RequestBody List<MenuItem> menuItems) {
		menuItemService.bulkUpdate(restaurantId, menuItems);
		return "";
	}
	
	@GetMapping("/restaurant/{restaurantId}/menuitems")
	public List<MenuItem> getMenuItems(@PathVariable("restaurantId") Long restaurantId) {
		List<MenuItem> menuItmes = menuItemService.findByRestaurantId(restaurantId);
		return menuItmes;
	}


}
