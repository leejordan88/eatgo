package kr.co.fastcampus.interfaces;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import kr.co.fastcampus.application.UserService;
import kr.co.fastcampus.domain.User;

@RestController
public class UserController {

	@Autowired
	UserService userService;
	
	//1. User list
	@GetMapping("/users")
	public List<User> list() {
		return userService.getUsers();
	}
	
	//2. User create -> 회원가입
	@PostMapping("/user")
	public ResponseEntity<?> create(
			@RequestBody User resource) throws URISyntaxException {
		String email = resource.getEmail();
		String name = resource.getName();
		Long level = resource.getLevel();
		User user = userService.addUser(email, name, level);
		URI uri = new URI("/user/" + user.getId());
		return ResponseEntity.created(uri).body("{}");
	}
	
	//3. User update
	@PatchMapping("/user/{id}")
	public User update(
			@PathVariable("id") Long id,
			@RequestBody User resource) {
		User user = userService.updateUser(id, resource.getEmail(), resource.getName(), resource.getLevel());
		return user;
	}
	
	//4. User delete -> level: 0 => 아무것도 못하게 만든다.
	//(1:customers, 2: restaurant owner, 3: admin)
	@DeleteMapping("/user/{id}")
	public String delete(@PathVariable("id") Long id) {
		userService.deactivateUser(id);
		return "{}";
	}
	
}
