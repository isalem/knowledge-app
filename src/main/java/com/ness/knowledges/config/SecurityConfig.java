package com.ness.knowledges.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/resources/**", "/", "/user/create").permitAll()
				.anyRequest().authenticated()
			.and()
			.formLogin()
				.usernameParameter("username")
				.passwordParameter("password")
			.and()
			.logout().logoutSuccessUrl("/")
			.and()
			.csrf();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
			.usersByUsernameQuery("select username, password, 'true' from users where username=?")
			.authoritiesByUsernameQuery("select username, role from users where username=?");
//		auth.inMemoryAuthentication()
//			.withUser("admin").password("admin").roles("USER", "ADMIN");
	}
}
 