package kr.co.fastcampus.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface RegionRepository extends CrudRepository<Region, Long> {

	List<Region> findAll();
	
	@SuppressWarnings("unchecked")
	Region save(Region region);
	

}
