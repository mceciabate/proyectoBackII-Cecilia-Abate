package com.digitalmedia.users.repository;

import com.digitalmedia.users.model.User;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@Repository
public class KeycloakUserRepository implements IUserRepository {
    @Autowired
    private Keycloak keycloackClient;

    @Value("${dh.keycloak.realm}")
    private String realm;


    @Override
    public List<User> findByFirstName(String name) {
        List<UserRepresentation> users = keycloackClient.realm(realm).users().search(name);
        return users.stream().map(userRepresentation -> toUser(userRepresentation)).collect(Collectors.toList());
    }

    @Override
    public Optional<User> findById(String id) {
        UserRepresentation user = keycloackClient.realm(realm).users().get(id).toRepresentation();
        return Optional.of(toUser(user));
    }


    @Override
    public User updateNationality(String id, String nacionalidad) {
        UserResource user = keycloackClient.realm(realm).users().get(id);
        UserRepresentation userRepresentation = user.toRepresentation();
        Map<String, List<String>> atributos = new HashMap<>();
        atributos.put("nacionalidad", List.of(nacionalidad));
        userRepresentation.setAttributes(atributos);
        user.update(userRepresentation);
        return toUser(userRepresentation);


    }

//    @Override
//    public User saveUserExtra(User userExtra) {
//        return null;
//    }
//
//    @Override
//    public User validateAndGetUser(String username) {
//        return null;
//    }
//
//    @Override
//    public Optional<User> getUserExtra(String username) {
//        return Optional.empty();
//    }


    private User toUser(UserRepresentation userRepresentation){
        String nationality = null;
        try {
            nationality = userRepresentation.getAttributes().get("nacionalidad").get(0);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return new  User(userRepresentation.getId(), userRepresentation.getUsername(),   userRepresentation.getEmail(), userRepresentation.getFirstName(), userRepresentation.getLastName());

    }
}
