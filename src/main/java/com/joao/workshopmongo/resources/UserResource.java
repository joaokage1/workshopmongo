package com.joao.workshopmongo.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.joao.workshopmongo.domain.User;
import com.joao.workshopmongo.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired
	private UserService service;

	public UserService getService() {
		return this.service;
	}

	public void setService(UserService service) {
		this.service = service;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<User>> fetchAllUsers() {

		return ResponseEntity.ok().body(getService().fetchAllUsers());

	}
}
