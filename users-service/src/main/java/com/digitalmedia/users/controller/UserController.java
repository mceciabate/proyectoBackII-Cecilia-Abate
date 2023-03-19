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
//Solo los administradores podrán consumir estos endpoints
public class UserController {
    @Autowired
    private  KeycloakUserRepository service;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_admin')")
    public List<User> getAll(){
        return service.getAll();

    }
    //endpoint que consume Feing en Bills
    @GetMapping("/name/{nombreUser}")
    @PreAuthorize("hasRole('ROLE_admin')")
    public User findByName(@PathVariable("nombreUser") String name){
        return service.findByUsername(name);
    }



}

