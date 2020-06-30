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
		return null;
	}

}
