package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.request.AuthenticationRequestDto;
import com.pragma.powerup.application.dto.request.RegisterRequestDto;
import com.pragma.powerup.application.dto.request.UserRequestDto;
import com.pragma.powerup.application.dto.response.JwtResponseDto;
import com.pragma.powerup.application.dto.response.UserResponseDto;

public interface IUserHandler {

    UserResponseDto register(UserRequestDto userRequestDto);

    JwtResponseDto login(AuthenticationRequestDto authenticationRequestDto);

    UserResponseDto getById(Long userId);

    UserResponseDto getByEmail(String email);

    UserResponseDto ownerRegister(RegisterRequestDto registerRequestDto);

    UserResponseDto employeeRegister(RegisterRequestDto registerRequestDto, Long restaurantId);

    UserResponseDto clientRegister(RegisterRequestDto registerRequestDto);
}
