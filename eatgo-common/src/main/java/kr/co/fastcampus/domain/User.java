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

@Entity
@Getter
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

	public User(@NotEmpty String email, @NotEmpty String name, Long level) {
		super();
		this.email = email;
		this.name = name;
		this.level = level;
	}

	public boolean isAdmin() {
		return level >= 100;
	}

}
