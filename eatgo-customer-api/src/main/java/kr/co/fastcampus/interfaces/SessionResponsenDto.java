package kr.co.fastcampus.interfaces;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SessionResponsenDto {
	private String accessToken;
}
