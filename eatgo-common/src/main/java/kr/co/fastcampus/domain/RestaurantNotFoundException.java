package kr.co.fastcampus.domain;

public class RestaurantNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public RestaurantNotFoundException(Long id) {
		super("Could not find restaurant " + id);
	}

}
