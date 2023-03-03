package com.digitalmedia.users.repository;

import com.digitalmedia.users.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;



public interface IMongoUserRepository extends MongoRepository<User,String> {
}


