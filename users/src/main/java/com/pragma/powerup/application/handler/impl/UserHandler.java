package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.AuthenticationRequestDto;
import com.pragma.powerup.application.dto.request.RegisterRequestDto;
import com.pragma.powerup.application.dto.request.RestaurantEmployeeRequestDto;
import com.pragma.powerup.application.dto.request.UserRequestDto;
import com.pragma.powerup.application.dto.response.JwtResponseDto;
import com.pragma.powerup.application.dto.response.UserResponseDto;
import com.pragma.powerup.application.handler.IJwtHandler;
import com.pragma.powerup.application.handler.IUserHandler;
import com.pragma.powerup.application.mapper.IRolResponseMapper;
import com.pragma.powerup.application.mapper.IUserRequestMapper;
import com.pragma.powerup.application.mapper.IUserResponseMapper;
import com.pragma.powerup.domain.api.IRolServicePort;
import com.pragma.powerup.domain.api.IUserServicePort;
import com.pragma.powerup.domain.model.RolModel;
import com.pragma.powerup.domain.model.UserModel;
import com.pragma.powerup.infrastructure.configuration.FeignClientInterceptorImp;
import com.pragma.powerup.infrastructure.exception.EmailAlreadyTaken;
import com.pragma.powerup.infrastructure.exception.NoDataFoundException;
import com.pragma.powerup.infrastructure.input.rest.Plazoleta.IPlazoletaService;
import com.pragma.powerup.infrastructure.out.jpa.entity.UserEntity;
import org.springframework.security.authentication.AuthenticationManager;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserHandler implements IUserHandler {

    private final IUserServicePort userServicePort;
    private final IUserRequestMapper userRequestMapper;
    private final IUserResponseMapper userResponseMapper;
    private final IJwtHandler jwtHandler;
    private final AuthenticationManager authenticationManager;
    private final IRolResponseMapper rolResponseMapper;
    private final IRolServicePort rolServicePort;
    private final IPlazoletaService plazoletaService;


    @Override
    public UserResponseDto register(UserRequestDto userRequestDto) {
        if (userServicePort.findUserByEmail(userRequestDto.getEmail()).isPresent()) {
            throw new EmailAlreadyTaken();
        }

        RolModel rolModel = rolServicePort.getRol(userRequestDto.getRolId());
        UserModel userModel = userRequestMapper.toUser(userRequestDto);
        userModel.setRolId(rolModel);

        return userResponseMapper.toResponse(userServicePort.saveUser(userModel), rolResponseMapper.toResponse(rolModel));
    }

    @Override
    public JwtResponseDto login(AuthenticationRequestDto authenticationRequestDto) {
        JwtResponseDto jwtResponseDto = new JwtResponseDto();

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequestDto.getEmail(),
                        authenticationRequestDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserEntity userEntity = (UserEntity) authentication.getPrincipal();

        var user = userServicePort.findUserByEmail(authenticationRequestDto.getEmail()).orElseThrow();
        var jwtToken = jwtHandler.generateToken(user);

        jwtResponseDto.setToken(jwtToken);
        jwtResponseDto.setBearer(userEntity.getEmail());
        jwtResponseDto.setUserName(userEntity.getName());
        jwtResponseDto.setAuthorities(userEntity.getAuthorities());

        return jwtResponseDto;
    }

    @Override
    public UserResponseDto getById(Long userId) {
        UserModel userModel = userServicePort.getById(userId);
        return userRequestMapper.toDto(userModel);
    }

    @Override
    public UserResponseDto getByEmail(String email) {
        UserModel userModel = userServicePort.findUserByEmailModel(email);
        return userRequestMapper.toDto(userModel);
    }

    @Override
    public UserResponseDto ownerRegister(RegisterRequestDto registerRequestDto) {
        if (userServicePort.findUserByEmail(registerRequestDto.getEmail()).isPresent()) {
            throw new EmailAlreadyTaken();
        }
        UserRequestDto userRequestDto = userRequestMapper.toUserRequestDto(registerRequestDto);
        UserModel userModel = userRequestMapper.toUser(userRequestDto);
        RolModel rolModel = rolServicePort.getRol(2L);
        userModel.setRolId(rolModel);

        return userResponseMapper.toResponse(userServicePort.saveUser(userModel), rolResponseMapper.toResponse(rolModel));
    }

    @Override
    public UserResponseDto employeeRegister(RegisterRequestDto registerRequestDto, Long restaurantId) {
        if (userServicePort.findUserByEmail(registerRequestDto.getEmail()).isPresent()) {
            throw new EmailAlreadyTaken();
        }

        String tokenHeader = FeignClientInterceptorImp.getBearerTokenHeader();
        String[] splited = tokenHeader.split("\\s+");
        String email = jwtHandler.extractUserName(splited[1]);

        Optional<UserEntity> userEntityOptional = userServicePort.findUserByEmail(email);

        if (userEntityOptional.isEmpty()) {
            throw new NoDataFoundException();
        }

        UserEntity owner = userEntityOptional.get();

        UserRequestDto userRequestDto = userRequestMapper.toUserRequestDto(registerRequestDto);

        UserModel userModel = userRequestMapper.toUser(userRequestDto);
        RolModel rolModel = rolServicePort.getRol(3L);
        userModel.setRolId(rolModel);

        UserModel userModelResponse = userServicePort.saveUser(userModel);

        RestaurantEmployeeRequestDto restaurantEmployeeRequestDto = new RestaurantEmployeeRequestDto();
        restaurantEmployeeRequestDto.setEmployeeId(userModelResponse.getId());
        restaurantEmployeeRequestDto.setRestaurantId(restaurantId);
        restaurantEmployeeRequestDto.setOwnerId(owner.getId());
        plazoletaService.saveRestaurantEmployee(restaurantEmployeeRequestDto);

        return userResponseMapper.toResponse(userModelResponse, rolResponseMapper.toResponse(rolModel));
    }

    @Override
    public UserResponseDto clientRegister(RegisterRequestDto registerRequestDto) {
        return null;
    }
}
