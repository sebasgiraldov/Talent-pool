package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.UserRequestDto;
import com.pragma.powerup.application.dto.response.UserResponseDto;
import com.pragma.powerup.application.handler.IUserHandler;
import com.pragma.powerup.application.mapper.IUserRequestMapper;
import com.pragma.powerup.application.mapper.IUserResponseMapper;
import com.pragma.powerup.domain.api.IUserServicePort;
import com.pragma.powerup.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional
public class UserHandler implements IUserHandler {

    private final IUserServicePort userServicePort;
    private final IUserRequestMapper userRequestMapper;
    private final IUserResponseMapper userResponseMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public void saveUser(UserRequestDto userRequestDto) {
        User user = userRequestMapper.toUser(userRequestDto);
        user.setRole("USER");
        user.setPass(passwordEncoder.encode(user.getPass()));
        userServicePort.saveUser(user);
    }

    @Override
    public void saveOwner(UserRequestDto userRequestDto) {
        User user = userRequestMapper.toUser(userRequestDto);
        user.setRole("OWNER");
        user.setPass(passwordEncoder.encode(user.getPass()));
        userServicePort.saveOwner(user);
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        return userResponseMapper.toResponseList(userServicePort.getAllUsers());
    }

    @Override
    public UserResponseDto getUser(int document) {
        User user = userServicePort.getUser(document);
        return userResponseMapper.toResponse(user);
    }

    @Override
    public void updateUser(UserRequestDto user) {
        User newUser = userRequestMapper.toUser(user);
        userServicePort.updateUser(newUser);
    }

    @Override
    public void deleteUser(int document) {
        User user = userServicePort.getUser(document);
        userServicePort.deleteUser(document);
    }
}
