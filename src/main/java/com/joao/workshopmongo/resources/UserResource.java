package com.joao.workshopmongo.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.joao.workshopmongo.domain.User;
import com.joao.workshopmongo.dto.UserDTO;
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
	public ResponseEntity<List<UserDTO>> fetchAllUsers() {
		return ResponseEntity.ok()
				.body(getService().fetchAllUsers().stream().map(x -> new UserDTO(x)).collect(Collectors.toList()));
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<UserDTO> fetchUserById(@PathVariable String id) {
		User user = getService().fetchById(id);
		return ResponseEntity.ok().body(new UserDTO(user));
	}
}
