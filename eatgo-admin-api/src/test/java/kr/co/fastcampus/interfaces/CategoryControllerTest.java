package kr.co.fastcampus.interfaces;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import kr.co.fastcampus.application.CategoryService;
import kr.co.fastcampus.domain.Category;

@WebMvcTest(CategoryController.class)
@ContextConfiguration(classes = CategoryController.class)
class CategoryControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private CategoryService categoryService;

	@Test
	public void list() throws Exception {
		List<Category> categorys = new ArrayList<Category>();
		categorys.add(Category.builder().name("korean food").build());
		given(categoryService.getCategorys()).willReturn(categorys);
		
		mvc.perform(get("/categorys"))
			.andExpect(status().isOk())
			.andExpect(content().string(containsString("korean food")));
	}
	
//	@Test
	public void create() throws Exception {
		Category category = Category.builder()
				.name("korean food")
				.build();
		given(categoryService.addCategory(category)).willReturn(
				Category.builder()
				.id(1L)
				.name("korean food")
				.build());
		
		mvc.perform(post("/category")
				.contentType(MediaType.APPLICATION_JSON)
				.content("\"korean food\""))
				.andExpect(status().isCreated())
				.andExpect(content().string("{}"));
		
	}

}
