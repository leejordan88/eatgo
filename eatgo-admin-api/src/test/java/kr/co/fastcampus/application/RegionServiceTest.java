package kr.co.fastcampus.application;

import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import kr.co.fastcampus.domain.Region;
import kr.co.fastcampus.domain.RegionRepository;

class RegionServiceTest {

	private RegionService regionService;
	
	@Mock
	private RegionRepository regionRepository;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		regionService = new RegionService(regionRepository);
	}
	
	@Test
	public void getRegions() {
		List<Region> mockRegions = new ArrayList<Region>();
		mockRegions.add(Region.builder().name("seoul").build());
		given(regionRepository.findAll()).willReturn(mockRegions);
		
		List<Region> regions = regionService.getRegions();
		Region region = regions.get(0);
		assertThat(region.getName(), is("seoul"));
	}

	@Test
	public void addRegion() {
		Region mockRegion = Region.builder().name("seoul").build();
		given(regionRepository.save(mockRegion)).willReturn(Region.builder().id(1L).name("seoul").build());
		Region region = regionService.addRegion(mockRegion);
		
		verify(regionRepository).save(any());
		assertThat(region.getName(), is("seoul"));
	}
	
}
