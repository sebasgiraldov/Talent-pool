package com.pragma.powerup.domain.spi;

import com.pragma.powerup.domain.model.User;

import java.util.List;

public interface IUserPersistencePort {

    User saveUser(User user);

    User saveOwner(User user);

    List<User> getAllUsers();

    User getUser(int document);

    void updateUser(User user);

    void deleteUser(int document);

}
