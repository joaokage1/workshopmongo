package com.joao.workshopmongo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.joao.workshopmongo.domain.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

}
