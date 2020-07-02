package kr.co.fastcampus.exception;

@SuppressWarnings("serial")
public class EmailNotExistedException extends RuntimeException {

	public EmailNotExistedException(String email) {
		super("Email is not registered " + email);
	}
	
}
