package com.certTrack.UserService.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name="users")
public class User {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String userName;
    private String email;
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
		// TODO Auto-generated constructor stub
	}
    
}

