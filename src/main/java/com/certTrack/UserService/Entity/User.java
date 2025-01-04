package com.certTrack.UserService.Entity;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name="users")
public class User implements UserDetails{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String userName;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;
    public int getId() {
    	return id;
    }
    public void setId(int id) {
    	this.id = id;
    }
    public String getUserName() {
    	return userName;
    }
    public void setUserName(String userName) {
    	this.userName = userName;
    }
    public String getEmail() {
    	return email;
    }
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}
	public void setPassword(String password) {
		this.password = password;;
	}
    public void setEmail(String email) {
    	this.email = email;
    }
    public Role getRole() {
    	return role;
    }
    public void setRole(Role role) {
    	this.role = role;
    }
	public User(int id, String userName, String email, Role role) {
		super();
		this.id = id;
		this.userName = userName;
		this.email = email;
		this.role = role;
	}
	public User() {
		super();
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(role.name()));
	}
	@Override
	public String getUsername() {
		return this.getUsername();
	}
    
}

