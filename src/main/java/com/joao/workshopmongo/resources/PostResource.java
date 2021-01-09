package com.joao.workshopmongo.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.joao.workshopmongo.domain.Post;
import com.joao.workshopmongo.services.PostService;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

	@Autowired
	private PostService service;

	public PostService getService() {
		return this.service;
	}

	public void setService(PostService service) {
		this.service = service;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<Post> fetchPostById(@PathVariable String id) {
		Post post = getService().fetchPostById(id);
		return ResponseEntity.ok().body(post);
	}
}
