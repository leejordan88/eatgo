package kr.co.fastcampus.interfaces;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import kr.co.fastcampus.application.MenuItemService;
import kr.co.fastcampus.domain.MenuItem;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class MenuItemControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private MenuItemService menuItemService;

	@Test
	@DisplayName("아이템 벌크 업데이트 테스트")
	public void bulkUpdate() throws Exception {
		mvc.perform(patch("/restaurant/1/menuitems").contentType(MediaType.APPLICATION_JSON)
				.content("[{\"restaurantId\":100,\"name\":\"짜장면\"}]")).andExpect(status().isOk());

		List<MenuItem> list = new ArrayList<MenuItem>();
		list.add(MenuItem.builder().id(1L).restaurantId(100L).name("짜장면").build());
		verify(menuItemService).bulkUpdate(1L, list);

	}
}
