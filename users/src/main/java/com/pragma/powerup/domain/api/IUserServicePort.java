package com.pragma.powerup.domain.api;

import com.pragma.powerup.domain.model.User;

import java.util.List;

public interface IUserServicePort {

    void saveUser(User user);

    void saveOwner(User user);

    List<User> getAllUsers();

    User getUser(int document);

    void updateUser(User user);

    void deleteUser(int document);

}
