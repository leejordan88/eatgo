package kr.co.fastcampus.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtUtil {

	private String key;
	
	public JwtUtil(String key) {
		this.key = key;
	}
	
//	ABCDEFGHIJK0123456789
	public String createToken(long userId, String name) {
		return Jwts.builder()
				.claim("userId", userId)
				.claim("name", name)
				.signWith(SignatureAlgorithm.HS256, key)
				.compact();
	}

	public Claims getClaims(String token) {
		return Jwts.parser()
			.setSigningKey(key)
			.parseClaimsJws(token)
			.getBody();
	}
	
}
