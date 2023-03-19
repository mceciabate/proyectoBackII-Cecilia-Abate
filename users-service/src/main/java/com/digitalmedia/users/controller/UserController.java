package com.digitalmedia.users.controller;

import com.digitalmedia.users.model.User;
import com.digitalmedia.users.model.dto.UserDTO;
import com.digitalmedia.users.repository.KeycloakUserRepository;
import com.digitalmedia.users.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private  KeycloakUserRepository service;


//    @GetMapping("/find/{id}")
//    public ResponseEntity<User> findById(@PathVariable String id){
//        return ResponseEntity.ok().body(service.findUserById(id));
//    }

    @GetMapping("/name/{nombreUser}")
    public User findByName(@PathVariable("nombreUser") String name){
        return service.findByUsername(name);
    }


//    @GetMapping("/admin")
//    @PreAuthorize("hasAuthority('ROLE_admin')")
//    public List<UserDTO> getAllNonAdminUsers(){
//      return userService.findAllNonAdminUsers();
//    }
//
//    @GetMapping("/admin/{username}")
//    @PreAuthorize("hasAuthority('ROLE_admin')")
//    public User getUserByUsername(@PathVariable("username") String username){
//      return userService.findByUsername(username);
//    }
//
//    @GetMapping("/findUsername/{username}")
//    public User getByUsername(@PathVariable("username") String username){
//      return userService.findByUsername(username);
//
//    }

}

