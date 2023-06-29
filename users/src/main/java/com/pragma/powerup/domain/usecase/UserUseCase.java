package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IUserServicePort;
import com.pragma.powerup.domain.model.User;
import com.pragma.powerup.domain.spi.IUserPersistencePort;

import java.util.List;

public class UserUseCase implements IUserServicePort {

    private final IUserPersistencePort userPersistencePort;

    public UserUseCase(IUserPersistencePort userPersistencePort) {
        this.userPersistencePort = userPersistencePort;
    }

    @Override
    public void saveUser(User user) {
        userPersistencePort.saveUser(user);
    }

    @Override
    public void saveOwner(User user) {
        userPersistencePort.saveOwner(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userPersistencePort.getAllUsers();
    }

    @Override
    public User getUser(int document) {
        return userPersistencePort.getUser(document);
    }

    @Override
    public void updateUser(User user) {
        userPersistencePort.updateUser(user);
    }

    @Override
    public void deleteUser(int document) {
        userPersistencePort.deleteUser(document);
    }
}
