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

//  public void UserService(SubscriptionRepository subscriptionRepository) {
//    this.subscriptionRepository = subscriptionRepository;
//    this.userRepository = List.of(new User("1a", "Tomas", "Pereyra", "tomas.pereyra@digitalhouse.com"));
//  }
//
//  public User findById(Integer id){
//    User user = userRepository.stream().filter(_user -> Objects.equals(_user.getId(), id)).findFirst().orElse(null);
//    SubscriptionDTO subscriptionDTO = subscriptionRepository.findByUserId(id);
//    if (user != null)
//      user.setSubscription(subscriptionDTO);
//
//    return user;
//  }

  public User findByUsername(String username){
    return keycloakRepository.findByUsername(username);
  }

//  public User findUserById(String id){
//    return keycloakRepository.findById(id);
//  }
//
//  public List<UserDTO> findAllNonAdminUsers(){
//    return keycloakRepository.findAll();
//  }



}
