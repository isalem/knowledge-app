package com.ness.knowledges.domain;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.ness.knowledges.security.UserRole;

@Entity
public class User implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private Long userId;
	
	@NotNull
	@Length(min = 1, max = 100)
	@Column(nullable = false, length = 100)
	private String name;
	
	@NotNull
	@Length(max = 100)
	@Column(nullable = false, length = 100)
	private String email;
	
	@NotNull
	@Length(min = 1, max = 30)
	@Column(nullable = false, length = 30)
	private String username;
	
	@NotNull
	@Column(nullable = false)
	private String password;
	
	@NotNull
	@Enumerated(EnumType.STRING)
	private UserRole authoritie;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Knowledge> knowledges;
	
	public User(String name, String email, String username, String password, UserRole authorities, Set<Knowledge> knowledges) {
		this.name = name;
		this.email = email;
		this.username = username;
		this.password = password;
		this.authoritie = authorities;
		this.knowledges = new HashSet<>(knowledges);
	}

	public User(String name, String email, String username, String password, UserRole authoritie) {
		this(name, email, username, password, authoritie, new HashSet<>());
	}
	
	protected User() { }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<Knowledge> getKnowledges() {
		return knowledges;
	}

	public void setKnowledges(Set<Knowledge> knowledges) {
		this.knowledges = knowledges;
	}

	@Override
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public GrantedAuthority getAuthoritie() {
		return authoritie;
	}

	public void setAuthoritie(UserRole authoritie) {
		this.authoritie = authoritie;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authorities = new HashSet<>();
		authorities.add(this.authoritie);
		return authorities;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	@Override
	public boolean isEnabled() {
		return true;
	}
}
