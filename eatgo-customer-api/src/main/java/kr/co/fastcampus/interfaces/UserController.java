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
public class UserController {

	@Autowired
	UserService userService;
	
	@PostMapping("/user")
	public ResponseEntity<?> create(
			@RequestBody User resource) throws URISyntaxException {
		User user = userService.registerUser(
				resource.getEmail(), resource.getName(), resource.getPassword(), resource.getLevel());
		URI uri = new URI("/user/" + user.getId());
		return ResponseEntity.created(uri).body("{}");
	}
	
}
