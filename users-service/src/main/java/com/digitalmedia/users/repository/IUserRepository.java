package com.digitalmedia.users.repository;

import com.digitalmedia.users.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserRepository {

//  User validateAndGetUser(String username);
//  User saveUserExtra(User userExtra);
//
//  Optional<User> getUserExtra(String username);

  public List<User> findByFirstName(String name);

  Optional<User> findById(String id);

  public User updateNationality(String id, String nacionalidad);


}
