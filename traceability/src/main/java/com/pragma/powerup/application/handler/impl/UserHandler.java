package com.pragma.powerup.application.handler.impl;
import com.pragma.powerup.application.dto.response.UserResponseDto;
import com.pragma.powerup.application.handler.IUserHandler;
import com.pragma.powerup.application.mapper.IUserRequestMapper;
import com.pragma.powerup.domain.api.IUserServicePort;
import com.pragma.powerup.domain.model.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserHandler implements IUserHandler {

    private final IUserServicePort userServicePort;
    private final IUserRequestMapper userRequestMapper;

    @Override
    public UserResponseDto getById(String userId) {
        UserModel userModel = userServicePort.getById(userId);
        return userRequestMapper.toDto(userModel);
    }

    @Override
    public UserResponseDto getByEmail(String email) {
        UserModel userModel = userServicePort.findUserByEmailModel(email);
        return userRequestMapper.toDto(userModel);
    }




}
