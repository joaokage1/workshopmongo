package com.joao.workshopmongo.dto;

import java.io.Serializable;

import com.joao.workshopmongo.domain.User;

@SuppressWarnings("serial")
public class UserDTO implements Serializable {

	private String id;
	private String name;
	private String email;

	public UserDTO() {

	}

	public UserDTO(User user) {
		super();
		this.id = user.getId();
		this.name = user.getName();
		this.email = user.getEmail();
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
