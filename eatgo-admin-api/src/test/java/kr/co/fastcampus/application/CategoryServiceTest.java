package kr.co.fastcampus.application;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import kr.co.fastcampus.domain.Category;
import kr.co.fastcampus.domain.CategoryRepository;

class CategoryServiceTest {

	private CategoryService categoryService;
	
	@Mock
	private CategoryRepository categoryRepository;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		categoryService = new CategoryService(categoryRepository);
	}
	
	@Test
	public void getRegions() {
		List<Category> mockCategorys = new ArrayList<Category>();
		mockCategorys.add(Category.builder().name("korean food").build());
		given(categoryRepository.findAll()).willReturn(mockCategorys);
		
		List<Category> categorys = categoryService.getCategorys();
		Category category = categorys.get(0);
		assertThat(category.getName(), is("korean food"));
	}

	@Test
	public void addRegion() {
		Category mockCategory = Category.builder().name("korean food").build();
		given(categoryRepository.save(mockCategory)).willReturn(Category.builder().id(1L).name("korean food").build());
		Category category = categoryService.addCategory(mockCategory);
		
		verify(categoryRepository).save(any());
		assertThat(category.getName(), is("korean food"));
	}
	
}
