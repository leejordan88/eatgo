package kr.co.fastcampus.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String address;
	
	@Transient
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<MenuItem> menuItems;
	
	@Transient
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<Review> reviews;

	public void updateInfomation(String name, String address) {
		this.name = name;
		this.address = address;
		
	}
	

	
}
