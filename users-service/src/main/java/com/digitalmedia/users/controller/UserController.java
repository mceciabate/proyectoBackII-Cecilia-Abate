package com.digitalmedia.users.controller;

import com.digitalmedia.users.model.User;
import com.digitalmedia.users.service.UserServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
//@RequestMapping("/users")
@RequestMapping("/api/v1/users")
public class UserController {


  private final UserServiceImpl userService;

  public UserController(UserServiceImpl userService) {
    this.userService = userService;
  }
  //TODO  estos dos endpoints funcionaran cuando este configurada la seguridad en el proyecto

  /*@GetMapping("/me")
  public User getUserExtra(Principal principal) {
    return userService.validateAndGetUserExtra(principal.getName());
  }*/

//  @GetMapping("/me")
//  public User getUserExtra(@RequestParam String principal) {
//    return userService.validateAndGetUserExtra(principal);
//  }
//
//  @PostMapping("/me")
//  public User saveUserExtra(@Valid @RequestBody UserRequest updateUserRequest, @RequestParam(value = "principal") String principal) {
//    Optional<User> userOptional = userService.getUserExtra(principal);
//    User userExtra = userOptional.orElseGet(() -> new User(principal, userRepresentation.getUsername(), userRepresentation.getEmail(), userRepresentation.getFirstName(), userRepresentation.getLastName()));
//    userExtra.setAvatar(updateUserRequest.getAvatar());
//    return userService.saveUserExtra(userExtra);
//  }

  @GetMapping("/name/{firstName}")
  public List<User> findByFirstName(@PathVariable  String firstName){
    return userService.findByFirstName(firstName);
  }

  @GetMapping("/id/{id}")
  public Optional<User> findById(@PathVariable  String id){
    return userService.findByID(id);
  }

  @PutMapping("/nacionUpdate")
  public User updateNacionality(@RequestParam   String id, @RequestParam String nacionalidad){
    return userService.updateNacionalidad(id, nacionalidad);
  }









}
