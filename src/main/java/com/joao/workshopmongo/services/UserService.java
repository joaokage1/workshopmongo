package com.joao.workshopmongo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joao.workshopmongo.domain.User;
import com.joao.workshopmongo.repositories.UserRepository;

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
}
