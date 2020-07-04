package kr.co.fastcampus.config;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import kr.co.fastcampus.filters.JwtAuthenticationFilter;
import kr.co.fastcampus.util.JwtUtil;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Value("${jwt.secret}")
	private String key;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		Filter filter = new JwtAuthenticationFilter(authenticationManager(), jwtUtil());
		
		http
			.csrf().disable()
			.cors().disable()
			.formLogin().disable()
			.headers().frameOptions().disable()
			.and()
			.addFilter(filter)
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	
	@Bean
	public JwtUtil jwtUtil() {
		return new JwtUtil(key);
	}

}
