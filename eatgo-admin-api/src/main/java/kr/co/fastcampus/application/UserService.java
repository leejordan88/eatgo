package kr.co.fastcampus.application;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.fastcampus.domain.User;
import kr.co.fastcampus.domain.UserRepository;

@Service
@Transactional
public class UserService {

	UserRepository userRepository;
	
	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public List<User> getUsers() {
		return userRepository.findAll();
	}

	public User addUser(String email, String name, Long level) {
		User user = User.builder()
				.email(email).name(name).level(level).build();
		return userRepository.save(user);
	}

	public User updateUser(Long id, String email, String name, Long level) {
		User user = userRepository.findById(id).orElse(null);
		user.setEmail(email);
		user.setName(name);
		user.setLevel(level);
		return userRepository.save(user);
	}

	public User deactivateUser(long id) {
		User user = userRepository.findById(id).orElse(null);
		user.deActivate();
		return userRepository.save(user);
	}

}
