package kr.co.fastcampus.domain;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import org.junit.jupiter.api.Test;

class RegionTest {

	@Test
	public void create() {
		Region region = Region.builder().name("서울").build();
		assertThat(region.getName(), is("서울"));
	}

	
	
}
