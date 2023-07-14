package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.request.AuthenticationRequestDto;
import com.pragma.powerup.application.dto.request.RegisterRequestDto;
import com.pragma.powerup.application.dto.request.UserRequestDto;
import com.pragma.powerup.application.dto.response.JwtResponseDto;
import com.pragma.powerup.application.dto.response.UserResponseDto;

public interface IUserHandler {

    UserResponseDto getById(String userId);

    UserResponseDto getByEmail(String email);

}
