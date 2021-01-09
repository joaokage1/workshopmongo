package com.joao.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.joao.workshopmongo.domain.Post;
import com.joao.workshopmongo.domain.User;
import com.joao.workshopmongo.dto.AuthorDTO;
import com.joao.workshopmongo.repositories.PostRepository;
import com.joao.workshopmongo.repositories.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {

	@Autowired
	private UserRepository repository;

	@Autowired
	private PostRepository postRepository;

	public UserRepository getRepository() {
		return this.repository;
	}

	public void setRepository(UserRepository repository) {
		this.repository = repository;
	}

	public PostRepository getPostRepository() {
		return this.postRepository;
	}

	public void setPostRepository(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	@Override
	public void run(String... args) throws Exception {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

		getRepository().deleteAll();
		getPostRepository().deleteAll();

		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");

		getRepository().saveAll(Arrays.asList(maria, alex, bob));

		Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu Viagem", "Vou viajar para São Paulo, Abraços",
				new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("23/03/2018"), "Saudades", "Saudades de sair com meu pai",
				new AuthorDTO(maria));

		getPostRepository().saveAll(Arrays.asList(post1, post2));

		maria.getPosts().addAll(Arrays.asList(post1, post2));
		getRepository().save(maria);
	}

}
