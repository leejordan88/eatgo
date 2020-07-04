package kr.co.fastcampus.util;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.jsonwebtoken.Claims;

class JwtUtilTest {
	
	JwtUtil jwtUtil;
	private static final String KEY = "ABCDEFGHIJK0123456789";
	
	@BeforeEach
	private void setUp() {
		this.jwtUtil = new JwtUtil(KEY);
	}
	
	@Test
	public void createToken() {
		String token = jwtUtil.createToken(1004L, "john");
		assertThat(token, containsString("."));
	}
	
	@Test
	public void getClaims() {
		String token = jwtUtil.createToken(1004L, "john");
		//eyJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOjEwMDQsIm5hbWUiOiJqb2huIn0.4j912axb1ITcgTTiPd-K-6Ie09cQ80MpvfO8L34JwsU
		Claims claims = jwtUtil.getClaims(token);
		assertEquals(claims.get("userId", Long.class), 1004L);
		assertEquals(claims.get("name"), "john");
	}
}
