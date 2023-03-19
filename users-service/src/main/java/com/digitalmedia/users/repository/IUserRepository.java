package com.digitalmedia.users.repository;

import com.digitalmedia.users.model.User;

public interface IUserRepository {
    User findByUsername(String username);

    User findById(String id);
}
