package com.joao.workshopmongo.resources;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.joao.workshopmongo.domain.User;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<User>> fetchAll() {
		User maria = new User("1", "Maria Silva", "Maria@gmail.com");
		User alex = new User("2", "Alex Ton", "Alex@gmail.com");

		return ResponseEntity.ok().body(Arrays.asList(maria, alex));
	}
}
