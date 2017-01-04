package com.skills.config;

import java.security.SecureRandom;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private static final byte[] SALT = { -32, -100, 22, -26, 117, -109, 18, 33, 53, 65, 63, 76, -97, 71, -99, -53 };
	
	@Autowired
	private DataSource dataSource;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(10, new SecureRandom(SALT));
	}
	
	@Bean
	public TokenBasedRememberMeServices rememberMeServices(UserDetailsService userDetailService) {
		return new TokenBasedRememberMeServices("remember-me-key", userDetailService);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/resources/**", "/", "/user/create").permitAll()
				.antMatchers("/dashboard/**").hasAnyRole("HR")
				.antMatchers("/skill/**").hasAnyRole("HR")
				.antMatchers("/user/search").hasAnyRole("HR", "BUSINESS")
				.anyRequest().authenticated()
			.and()
			.formLogin()
				.loginPage("/login")
				.permitAll()
				.defaultSuccessUrl("/", true)
				.usernameParameter("username")
				.passwordParameter("password")
			.and()
			.logout()
				.logoutUrl("/logout")
				.permitAll()
				.invalidateHttpSession(true)
				.deleteCookies("JSESSIONID");
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
			.passwordEncoder(passwordEncoder())
			.usersByUsernameQuery("select username, password, 'true' from user where username=?")
			.authoritiesByUsernameQuery("select username, authoritie from user where username=?");
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}
 