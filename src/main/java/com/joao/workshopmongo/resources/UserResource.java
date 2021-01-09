package com.joao.workshopmongo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.joao.workshopmongo.domain.Post;
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
		User user = getService().fetchUserById(id);
		return ResponseEntity.ok().body(new UserDTO(user));
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insertUser(@RequestBody UserDTO userDTO) {
		User user = getService().fromUserDTO(userDTO);
		user = getService().insertUser(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable String id) {
		getService().deleteUser(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public ResponseEntity<Void> updateUser(@RequestBody UserDTO userDTO, @PathVariable String id) {
		User user = getService().fromUserDTO(userDTO);
		user.setId(id);
		user = getService().updateUser(user);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}/posts")
	public ResponseEntity<List<Post>> fetchAllPostsByUser(@PathVariable String id) {
		User user = getService().fetchUserById(id);
		return ResponseEntity.ok().body(user.getPosts());
	}
}
