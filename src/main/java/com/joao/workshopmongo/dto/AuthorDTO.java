package com.joao.workshopmongo.dto;

import java.io.Serializable;

import com.joao.workshopmongo.domain.User;

@SuppressWarnings("serial")
public class AuthorDTO implements Serializable {

	private String id;
	private String name;

	public AuthorDTO() {

	}

	public AuthorDTO(User user) {
		setId(user.getId());
		setName(user.getName());
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

}
