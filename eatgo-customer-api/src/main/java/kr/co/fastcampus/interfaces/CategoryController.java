package kr.co.fastcampus.interfaces;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.fastcampus.application.CategoryService;
import kr.co.fastcampus.domain.Category;

@RestController
public class CategoryController {
	@Autowired
	CategoryService categoryService; 
	
	@GetMapping("/categorys")
	public List<Category> list() {
		List<Category> regions = categoryService.getCategorys();
		return regions;
	}
	
}
