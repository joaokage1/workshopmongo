package com.joao.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joao.workshopmongo.domain.User;
import com.joao.workshopmongo.dto.UserDTO;
import com.joao.workshopmongo.repositories.UserRepository;
import com.joao.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	public UserRepository getRepository() {
		return this.repository;
	}

	public void setRepository(UserRepository repository) {
		this.repository = repository;
	}

	public List<User> fetchAllUsers() {
		return getRepository().findAll();
	}

	public User fetchUserById(String id) {
		Optional<User> user = getRepository().findById(id);
		return user.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}

	public User insertUser(User user) {
		return getRepository().insert(user);
	}

	public void deleteUser(String id) {
		fetchUserById(id);
		getRepository().deleteById(id);
	}

	public User updateUser(User user) {
		User newUser = fetchUserById(user.getId());
		updateData(newUser, user);
		return getRepository().save(newUser);
	}

	private void updateData(User newObj, User obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
	}

	public User fromUserDTO(UserDTO userDTO) {
		return new User(userDTO.getId(), userDTO.getName(), userDTO.getEmail());
	}
}
