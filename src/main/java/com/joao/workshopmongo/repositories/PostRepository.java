package com.joao.workshopmongo.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.joao.workshopmongo.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

	List<Post> findByTitleContainingIgnoreCase(String text);

	@Query("{'title': {$regex: ?0, $options: 'i'}}")
	List<Post> fetchPostsByTitle(String text);

	@Query("{ $and: [\r\n"
			+ "        {'date': {$gte: ?1}},\r\n"
			+ "        {'date': {$lte: ?2}},\r\n"
			+ "        {$or: [\r\n"
			+ "                {'title': {$regex: ?0, $options: 'i'}},\r\n"
			+ "                {'body': {$regex: ?0, $options: 'i'}},\r\n"
			+ "                {'comments.text': {$regex: ?0, $options: 'i'}}\r\n"
			+ "            ]\r\n"
			+ "        }\r\n"
			+ "    ]\r\n"
			+ "}")
	List<Post> fullSearch(String text, Date minDate, Date maxDate);
}
