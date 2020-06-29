package kr.co.fastcampus.interfaces;

import java.net.URI;
import java.net.URISyntaxException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import kr.co.fastcampus.application.ReviewService;
import kr.co.fastcampus.domain.Review;

@Controller
public class ReviewController {

	@Autowired
	private ReviewService reviewService;

	@PostMapping("restaurant/{restaurantId}/review")
	public ResponseEntity<?> create(@PathVariable("restaurantId") Long restaurantId,
			@Valid @RequestBody Review resource) throws URISyntaxException {
		Review review = reviewService.addReview(restaurantId, resource);
		String uri = "/restaurant/" + restaurantId + "/review/" + review.getId();
		return ResponseEntity.created(new URI(uri)).body("{}");
	}

}
