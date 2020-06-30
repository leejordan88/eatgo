package kr.co.fastcampus.interfaces;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import kr.co.fastcampus.application.RestaurantService;
import kr.co.fastcampus.domain.MenuItem;
import kr.co.fastcampus.domain.Restaurant;
import kr.co.fastcampus.domain.RestaurantNotFoundException;

@WebMvcTest(RestaurantController.class)
class RestaurantControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private RestaurantService restaurantService;

	@Test
	void list() throws Exception {
		List<Restaurant> restaurants = new ArrayList<>();
		Restaurant restaurant = Restaurant.builder().id(1004L).name("Joonung").address("Anyang").build();
		restaurants.add(restaurant);
		given(restaurantService.getRestaurants("Anyang", 1L)).willReturn(restaurants);

		mvc.perform(get("/restaurants")).andExpect(status().isOk());

	}

	@Test
	void testDetail() throws Exception {
		Restaurant restaurant1 = Restaurant.builder().id(1004L).name("JoonSung").address("AnYang").build();
		restaurant1.setMenuItems(Arrays.asList(MenuItem.builder().id(1L).restaurantId(1004L).name("Kimchi").build()));

		Restaurant restaurant2 = Restaurant.builder().id(2080L).name("ChangMin").address("Seoul").build();

		given(restaurantService.getRestaurantById(1004L)).willReturn(restaurant1);
		given(restaurantService.getRestaurantById(2080L)).willReturn(restaurant2);

		mvc.perform(get("/restaurant/1004")).andExpect(status().isOk())
				.andExpect(content().string(containsString("\"id\":1004")))
				.andExpect(content().string(containsString("\"name\":\"JoonSung\"")))
				.andExpect(content().string(containsString("\"address\":\"AnYang\"")))
				.andExpect(content().string(containsString("Kimchi")));

		mvc.perform(get("/restaurant/2080")).andExpect(status().isOk())
				.andExpect(content().string(containsString("\"id\":2080")))
				.andExpect(content().string(containsString("\"name\":\"ChangMin\"")))
				.andExpect(content().string(containsString("\"address\":\"Seoul\"")));
	}

	@Test
	public void detailWithNotExisted() throws Exception {
		given(restaurantService.getRestaurantById(404L)).willThrow(new RestaurantNotFoundException(404L));

		mvc.perform(get("/restaurant/404")).andExpect(status().isNotFound()).andExpect(content().string("{}"));
	}


}
