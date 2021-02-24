package com.example.test.dal;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.test.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
}
