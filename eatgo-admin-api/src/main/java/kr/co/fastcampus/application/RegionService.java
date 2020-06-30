package kr.co.fastcampus.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.fastcampus.domain.Region;
import kr.co.fastcampus.domain.RegionRepository;

@Service
public class RegionService {
	
	private RegionRepository regionRepository;

	@Autowired
	public RegionService(RegionRepository regionRepository) {
		this.regionRepository = regionRepository;
	}

	public List<Region> getRegions() {
		List<Region> list = regionRepository.findAll();
		return list;
	}

	public Region addRegion(Region region) {
		return regionRepository.save(region);
	}

	
	
}
