package com.pragma.powerup.domain.spi;

import com.pragma.powerup.domain.model.UserModel;
import com.pragma.powerup.infrastructure.out.jpa.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface IUserPersistencePort {

    Optional<UserEntity> findUserByEmail(String email);
    UserModel findUserByEmailModel(String email);
    UserModel getById(String userId);

}
