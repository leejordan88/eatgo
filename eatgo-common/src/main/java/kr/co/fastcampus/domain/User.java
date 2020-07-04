package kr.co.fastcampus.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
	@Id
	@GeneratedValue
	private Long id;

	@NotEmpty
	private String email;

	@NotEmpty
	private String name;

	@NotNull
	private Long level;
	
	@NotEmpty
	private String password;
	
	public boolean isAdmin() {
		return level >= 100;
	}

	public boolean isActive() {
		return level > 0;
	}

	public void deActivate() {
		level = 0L;
	}

}
