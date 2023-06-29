package com.pragma.powerup.infrastructure.out.jpa.adapter;

import com.pragma.powerup.domain.model.User;
import com.pragma.powerup.domain.spi.IUserPersistencePort;
import com.pragma.powerup.infrastructure.exception.NoDataFoundException;
import com.pragma.powerup.infrastructure.exception.UserException;
import com.pragma.powerup.infrastructure.exception.UserNotFoundException;
import com.pragma.powerup.infrastructure.out.jpa.entity.UserEntity;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IUserEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.IUserRepository;
import com.pragma.powerup.infrastructure.out.jpa.utils.ValidationUtils;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
public class UserJpaAdapter implements IUserPersistencePort {
    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;

    @Override
    public User saveUser(User user) throws UserException {
        if (validateUser(user)) {
            UserEntity userEntity = userRepository.save(userEntityMapper.toEntity(user));
            return userEntityMapper.toUser(userEntity);
        } else {
            throw new UserException("Incorrect data");
        }
    }

    @Override
    public User saveOwner(User user) throws UserException {
        if (validateUser(user)) {
            UserEntity userEntity = userRepository.save(userEntityMapper.toEntity(user));
            return userEntityMapper.toUser(userEntity);
        } else {
            throw new UserException("Incorrect data");
        }
    }

    private boolean validateUser(User user) {
        LocalDate date = LocalDate.parse(user.getBirthDate());
        return ValidationUtils.isStringLengthInRange(user.getPhone(), 1, 13) &&
                !ValidationUtils.isNullOrEmpty(user.getName()) &&
                !ValidationUtils.isNullOrEmpty(user.getLastname()) &&
                !ValidationUtils.isNullOrEmpty(String.valueOf(user.getDocument())) &&
                !ValidationUtils.isNullOrEmpty(user.getBirthDate()) &&
                !ValidationUtils.isNullOrEmpty(user.getEmail()) &&
                !ValidationUtils.isNullOrEmpty(user.getPass()) &&
                ValidationUtils.isValidPhoneNumber(user.getPhone()) &&
                ValidationUtils.isValidEmail(user.getEmail()) &&
                ValidationUtils.isNumericText(String.valueOf(user.getDocument())) &&
                ValidationUtils.isAdult(date);
    }

    @Override
    public List<User> getAllUsers() {
        List<UserEntity> userEntityList = userRepository.findAll();
        if (userEntityList.isEmpty()) {
            throw new NoDataFoundException();
        }
        return userEntityMapper.toUserList(userEntityList);
    }

    @Override
    public User getUser(int document) {
        return userEntityMapper.toUser(userRepository.findByDocument(document)
                .orElseThrow(UserNotFoundException::new));
    }

    @Override
    public void updateUser(User user) {
        userRepository.save(userEntityMapper.toEntity(user));
    }

    @Override
    public void deleteUser(int document) {
        userRepository.deleteByDocument(document);
    }
}
