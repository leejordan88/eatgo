package kr.co.fastcampus.interfaces;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@PostMapping("/category")
	public ResponseEntity<?> create(@RequestBody Category resource) throws URISyntaxException {
		Category category = categoryService.addCategory(resource);
		URI uri = new URI("/region" + category.getId());
		return ResponseEntity.created(uri).body(category);
	}
}
