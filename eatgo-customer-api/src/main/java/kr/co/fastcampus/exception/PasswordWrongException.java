package kr.co.fastcampus.exception;

@SuppressWarnings("serial")
public class PasswordWrongException extends RuntimeException {
	
	public PasswordWrongException() {
		super("Password is wrong");
	}

}
