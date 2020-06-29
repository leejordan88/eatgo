package kr.co.fastcampus.application;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import kr.co.fastcampus.domain.MenuItem;
import kr.co.fastcampus.domain.MenuItemRepository;

class MenuItemServiceTest {

	private MenuItemService menuItemService;

	@Mock
	private MenuItemRepository menuItemRepository;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		menuItemService = new MenuItemService(menuItemRepository);
	}

	@Test
	@DisplayName("아이템 벌크 인서트")
	void bulkUpdate() {
		List<MenuItem> menuItems = new ArrayList<MenuItem>();
		menuItems.add(MenuItem.builder().name("Kimchi").build());
		menuItems.add(MenuItem.builder().id(12L).name("Kimchi").build());
		menuItems.add(MenuItem.builder().id(1004L).destroy(true).build());

		menuItemService.bulkUpdate(1L, menuItems);

		verify(menuItemRepository, times(2)).save(any());
		verify(menuItemRepository, times(1)).deleteById(eq(1004L));
	}

}
