package kr.co.fastcampus.interfaces;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import kr.co.fastcampus.application.RegionService;
import kr.co.fastcampus.domain.Region;

@WebMvcTest(RegionController.class)
@ContextConfiguration(classes = RegionController.class)
class RegionControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@MockBean
	private RegionService regionService;

	@Test
	public void list() throws Exception {
		List<Region> regions = new ArrayList<Region>();
		regions.add(Region.builder().name("seoul").build());
		given(regionService.getRegions()).willReturn(regions);
		
		mvc.perform(get("/regions"))
			.andExpect(status().isOk())
			.andExpect(content().string(containsString("seoul")));
	}
	
//	@Test
	public void create() throws Exception {
		Region region = Region.builder()
				.name("seoul")
				.build();
		given(regionService.addRegion(region)).willReturn(
				Region.builder()
				.id(1L)
				.name("seoul")
				.build());
		
		mvc.perform(post("/region")
				.contentType(MediaType.APPLICATION_JSON)
				.content("\"seoul\""))
				.andExpect(status().isCreated())
				.andExpect(content().string("{}"));
		
	}

}
