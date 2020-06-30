package kr.co.fastcampus.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.fastcampus.domain.Category;
import kr.co.fastcampus.domain.CategoryRepository;

@Service
public class CategoryService {
	private CategoryRepository categoryRepository;

	@Autowired
	public CategoryService(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	public List<Category> getCategorys() {
		List<Category> list = categoryRepository.findAll();
		return list;
	}

}
