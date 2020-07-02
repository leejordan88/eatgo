package kr.co.fastcampus.interfaces;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import kr.co.fastcampus.application.UserService;
import kr.co.fastcampus.domain.User;

@RestController
public class SessionController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/session")
	public ResponseEntity<SessionResponsenDto> create(
			@RequestBody SessionRequestDto resource) throws URISyntaxException {
		URI uri = new URI("/session");
		
		User user = userService.authenticate(resource.getEmail(), resource.getPassword());
		
		return ResponseEntity.created(uri).body(
				SessionResponsenDto.builder()
				.accessToken(user.getAccessToken())
				.build());
	}
}
