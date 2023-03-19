package com.digitalmedia.users.service;

import com.digitalmedia.users.model.User;
import com.digitalmedia.users.model.dto.UserDTO;
import com.digitalmedia.users.repository.KeycloakUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl {

  private List<User> userRepository;



  @Autowired
  private KeycloakUserRepository keycloakRepository;


  public User findByUsername(String username){
    return keycloakRepository.findByUsername(username);
  }

  public User findUserById(String id){
    return keycloakRepository.findById(id);
  }

  public List<User> getAll(){
    return keycloakRepository.getAll();
  }



}
