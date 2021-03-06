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
	
	@PostMapping("/region")
	public ResponseEntity<?> create(@RequestBody Region resource) throws URISyntaxException {
		Region region = regionService.addRegion(resource);
		URI uri = new URI("/region" + region.getId());
		return ResponseEntity.created(uri).body(region);
	}
	
}
