package com.joao.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joao.workshopmongo.domain.Post;
import com.joao.workshopmongo.repositories.PostRepository;
import com.joao.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository repository;

	public PostRepository getRepository() {
		return this.repository;
	}

	public void setRepository(PostRepository repository) {
		this.repository = repository;
	}

	public Post fetchPostById(String id) {
		Optional<Post> post = getRepository().findById(id);
		return post.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}

	public List<Post> fetchPostsByTitle(String search) {
		return getRepository().fetchPostsByTitle(search);
	}

}
