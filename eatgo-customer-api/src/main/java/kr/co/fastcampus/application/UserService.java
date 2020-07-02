package kr.co.fastcampus.application;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.co.fastcampus.domain.User;
import kr.co.fastcampus.domain.UserRepository;
import kr.co.fastcampus.exception.EmailExistedException;
import kr.co.fastcampus.exception.PasswordWrongException;

@Service
@Transactional
public class UserService {

	UserRepository userRepository;
	PasswordEncoder passwordEncoder;
	
	@Autowired
	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public User registerUser(String email, String name, String password, Long level) {
		
		Optional<User> existed = userRepository.findByEmail(email);
		if (existed.isPresent()) {
			throw new EmailExistedException(email);
		}
		
		User user = User.builder()
				.email(email)
				.name(name)
				.password(passwordEncoder(password))
				.level(level)
				.build();
		return userRepository.save(user);
	}

	public User authenticate(String email, String password) {
		User user = userRepository.findByEmail(email).orElseThrow(() -> new EmailExistedException(email));
		if (!isCorrectPassord(user.getPassword(), password)) {
			throw new PasswordWrongException();
		}
		return user;
	}
	
	public String passwordEncoder(String password) {
		return passwordEncoder.encode(password);
	}
	
	public boolean isCorrectPassord(String rawPassword, String encodedPassword) {
		return passwordEncoder.matches(rawPassword, encodedPassword);
	};
}
