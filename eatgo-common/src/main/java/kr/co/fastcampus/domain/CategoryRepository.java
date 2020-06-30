package kr.co.fastcampus.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {

	List<Category> findAll();
	
	@SuppressWarnings("unchecked")
	Category save(Category category);
	
}
