package kr.co.fastcampus.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Review {
	
	@Id
	@GeneratedValue
	Long id;
	
	private Long restaurantId;
	
	@NotEmpty
	private String name;
	
	@NotNull
	@Min(0)
	@Max(5)
	private int score;
	
	@NotEmpty
	private String description;
	
	
	
}
