package com.digitalmedia.users.service;

import com.digitalmedia.users.model.User;
import com.digitalmedia.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

  private final UserRepository userRepository;


  public List<User> findByFirstName(String firstName){

    return userRepository.findByFirstName(firstName);
  }

  public Optional<User> findByID(String id){
    return userRepository.findById(id);
  }

  public User updateNacionalidad(String id, String nacionalidad){
    return userRepository.updateNacionality(id, nacionalidad);
  }


//  @Override
//  public User validateAndGetUserExtra(String username) {
//    return userRepository.validateAndGetUser(username);
//  }
//
//  @Override
//  public Optional<User> getUserExtra(String username) {
//    return userRepository.getUserExtra(username);
//  }
//
//  @Override
//  public User saveUserExtra(User user) {
//    return userRepository.saveUserExtra(user);
//  }


}
