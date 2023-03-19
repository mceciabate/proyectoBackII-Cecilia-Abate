package com.digitalmedia.users.repository;

import com.digitalmedia.users.model.dto.UserDTO;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IMongoUserRepository extends MongoRepository<UserDTO, String> {


}
