package com.joao.workshopmongo.resources;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.joao.workshopmongo.domain.Post;
import com.joao.workshopmongo.resources.util.URL;
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

	@RequestMapping(method = RequestMethod.GET, value = "/titlesearch")
	public ResponseEntity<List<Post>> fetchPostsByTitle(@RequestParam(value = "text", defaultValue = "") String text) {
		text = URL.decodeParam(text);
		List<Post> posts = getService().fetchPostsByTitle(text);
		return ResponseEntity.ok().body(posts);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/fullsearch")
	public ResponseEntity<List<Post>> fetchPostsByStringAndDate(
			@RequestParam(value = "text", defaultValue = "") String text,
			@RequestParam(value = "minDate", defaultValue = "") String minDate,
			@RequestParam(value = "maxDate", defaultValue = "") String maxDate) {
		text = URL.decodeParam(text);
		Date min = URL.convertDate(minDate, new Date(0L));
		Date max = URL.convertDate(maxDate, new Date(0L));
		List<Post> posts = getService().fetchPostsByStringAndDate(text, min, max);
		return ResponseEntity.ok().body(posts);
	}
}
