package kr.co.fastcampus.exception;

public class EmailExistedException extends RuntimeException {
	
	private static final long serialVersionUID = 2091545750014027241L;

	public EmailExistedException(String email) {
		super("Email is already registered " + email);
	}
	
}
