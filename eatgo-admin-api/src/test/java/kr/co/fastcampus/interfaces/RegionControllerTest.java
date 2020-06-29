package kr.co.fastcampus.interfaces;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(RegionController.class)
class RegionControllerTest {

	@Autowired
	private MockMvc mvc;

	@Test
	public void list() throws Exception {
		//TODO: 지역정보 추가할 수 있도록 할 것
		mvc.perform(get("/regins"))
			.andExpect(status().isOk());
	}
	
}
