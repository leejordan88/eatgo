package kr.co.fastcampus.interfaces;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.fastcampus.application.RegionService;
import kr.co.fastcampus.domain.Region;

@RestController
public class RegionController {

	@Autowired
	RegionService regionService; 
	
	@GetMapping("/regions")
	public List<Region> list() {
		List<Region> regions = regionService.getRegions();
		return regions;
	}
	
	
}
