package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.RegisterRequestDto;
import com.pragma.powerup.application.dto.request.UserRequestDto;
import com.pragma.powerup.application.dto.response.ResponseDto;
import com.pragma.powerup.application.dto.response.RolResponseDto;
import com.pragma.powerup.application.dto.response.UserResponseDto;
import com.pragma.powerup.application.handler.IJwtHandler;
import com.pragma.powerup.application.handler.impl.Factory.FactoryUserDataTest;
import com.pragma.powerup.application.mapper.IRolResponseMapper;
import com.pragma.powerup.application.mapper.IUserRequestMapper;
import com.pragma.powerup.application.mapper.IUserResponseMapper;
import com.pragma.powerup.domain.api.IRolServicePort;
import com.pragma.powerup.domain.api.IUserServicePort;
import com.pragma.powerup.domain.model.UserModel;
import com.pragma.powerup.infrastructure.configuration.FeignClientInterceptorImp;
import com.pragma.powerup.infrastructure.exception.EmailAlreadyTaken;
import com.pragma.powerup.infrastructure.exception.NoDataFoundException;
import com.pragma.powerup.infrastructure.input.rest.Plazoleta.IPlazoletaService;
import com.pragma.powerup.infrastructure.out.jpa.entity.UserEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(SpringExtension.class)
class UserHandlerTest {

    @InjectMocks
    UserHandler userHandler;

    @Mock
    IUserServicePort userServicePort;
    @Mock
    IUserRequestMapper userRequestMapper;
    @Mock
    IUserResponseMapper userResponseMapper;
    @Mock
    IRolServicePort rolServicePort;
    @Mock
    IRolResponseMapper rolResponseMapper;
    @Mock
    IJwtHandler jwtHandler;
    @Mock
    IPlazoletaService plazoletaService;

    @Test
    void mustRegisterAnUser() {
        UserRequestDto userRequestDto = FactoryUserDataTest.getUserRequestDto(1L);
        UserResponseDto userResponseDto = FactoryUserDataTest.getUserResponseDto();
        UserModel expectedUser = FactoryUserDataTest.getUserModel(1L, "ROLE_ADMINISTRADOR");
        RolResponseDto rolResponseDto = FactoryUserDataTest.getRolResponseDto();

        Mockito.when(userServicePort.findUserByEmail(any())).thenReturn(Optional.empty());
        Mockito.when(rolServicePort.getRol(any())).thenReturn(expectedUser.getRolId());
        Mockito.when(userRequestMapper.toUser(any())).thenReturn(expectedUser);
        Mockito.when(rolResponseMapper.toResponse(any())).thenReturn(rolResponseDto);
        Mockito.when(userResponseMapper.toResponse(any(), any())).thenReturn(userResponseDto);

        Assertions.assertEquals(userResponseDto, userHandler.register(userRequestDto));

        Mockito.verify(userServicePort).saveUser(any(UserModel.class));
    }

    @Test
    void throwEmailAlreadyTakenExceptionWhenAttemptToRegisterAnUser() {

        UserRequestDto userRequestDto = FactoryUserDataTest.getUserRequestDto(1L);
        UserEntity userEntity = FactoryUserDataTest.getUserEntity();

        Mockito.when(userServicePort.findUserByEmail(any())).thenReturn(Optional.of(userEntity));

        Assertions.assertThrows(
                EmailAlreadyTaken.class,
                () -> userHandler.register(userRequestDto)
        );
    }


    @Test
    void mustRegisterAnOwner(){
        RegisterRequestDto registerRequestDto = FactoryUserDataTest.getRegisterRequestDto();
        UserRequestDto userRequestDto = FactoryUserDataTest.getUserRequestDto(2L);
        UserModel userModel = FactoryUserDataTest.getUserModel(2l, "ROLE_PROPIETARIO");
        UserResponseDto userResponseDto = FactoryUserDataTest.getUserResponseDto();

        Mockito.when(userServicePort.findUserByEmail(any())).thenReturn(Optional.empty());
        Mockito.when(userRequestMapper.toUserRequestDto(any())).thenReturn(userRequestDto);
        Mockito.when(userRequestMapper.toUser(any())).thenReturn(userModel);
        Mockito.when(rolServicePort.getRol(any())).thenReturn(userModel.getRolId());
        Mockito.when(userResponseMapper.toResponse(any(), any())).thenReturn(userResponseDto);

        Assertions.assertEquals(userResponseDto, userHandler.ownerRegister(registerRequestDto));

        Mockito.verify(userServicePort).saveUser(any(UserModel.class));

    }


    @Test
    void throwEmailAlreadyTakenExceptionWhenAttemptToRegisterAnOwner() {
        RegisterRequestDto registerRequestDto = FactoryUserDataTest.getRegisterRequestDto();
        UserEntity userEntity = FactoryUserDataTest.getUserEntity();

        Mockito.when(userServicePort.findUserByEmail(any())).thenReturn(Optional.of(userEntity));

        Assertions.assertThrows(
                EmailAlreadyTaken.class,
                () -> userHandler.ownerRegister(registerRequestDto)
        );
    }



    @Test
    void mustRegisterAnEmployee(){
        RegisterRequestDto registerRequestDto = FactoryUserDataTest.getRegisterRequestDto();
        UserRequestDto userRequestDto = FactoryUserDataTest.getUserRequestDto(3L);
        UserModel userModel = FactoryUserDataTest.getUserModel(3l, "ROLE_EMPLEADO");
        UserResponseDto userResponseDto = FactoryUserDataTest.getUserResponseDto();
        ResponseEntity<ResponseDto> response = FactoryUserDataTest.getResponseEntity();
        UserEntity userEntity2 = FactoryUserDataTest.getUserEntity2();

        try(MockedStatic<FeignClientInterceptorImp> utilities = Mockito.mockStatic(FeignClientInterceptorImp.class)){
            utilities.when(FeignClientInterceptorImp::getBearerTokenHeader).thenReturn("Bearer token");
            Mockito.when(userServicePort.findUserByEmail(registerRequestDto.getEmail())).thenReturn(Optional.empty());
            Mockito.when(jwtHandler.extractUserName(any())).thenReturn("sebasgiraldov2@gmail.com");
            Mockito.when(userServicePort.findUserByEmail("sebasgiraldov2@gmail.com")).thenReturn(Optional.of(userEntity2));
            Mockito.when(userServicePort.saveUser(any())).thenReturn(userModel);
            Mockito.when(userRequestMapper.toUserRequestDto(any())).thenReturn(userRequestDto);
            Mockito.when(userRequestMapper.toUser(any())).thenReturn(userModel);
            Mockito.when(rolServicePort.getRol(any())).thenReturn(userModel.getRolId());
            Mockito.when(userResponseMapper.toResponse(any(),any())).thenReturn(userResponseDto);
            Mockito.when(plazoletaService.saveRestaurantEmployee(any())).thenReturn(response);

            Assertions.assertEquals(userResponseDto, userHandler.employeeRegister(registerRequestDto, 3L));

            Mockito.verify(userServicePort).saveUser(any(UserModel.class));
        }
    }

    @Test
    void throwEmailAlreadyTakenExceptionWhenAttempToRegisterAnEmployee(){
        RegisterRequestDto registerRequestDto = FactoryUserDataTest.getRegisterRequestDto();
        UserEntity userEntity = FactoryUserDataTest.getUserEntity();

        Mockito.when(userServicePort.findUserByEmail(any())).thenReturn(Optional.of(userEntity));

        Assertions.assertThrows(
                EmailAlreadyTaken.class,
                () -> userHandler.employeeRegister(registerRequestDto, 3L)
        );
    }


    @Test
    void throwNoDataFoundExceptionWhenAttempToRegisterAnEmployee(){
        RegisterRequestDto registerRequestDto = FactoryUserDataTest.getRegisterRequestDto();
        UserRequestDto userRequestDto = FactoryUserDataTest.getUserRequestDto(3L);
        UserModel userModel = FactoryUserDataTest.getUserModel(3l, "ROLE_EMPLEADO");
        UserResponseDto userResponseDto = FactoryUserDataTest.getUserResponseDto();
        ResponseEntity<ResponseDto> response = FactoryUserDataTest.getResponseEntity();
        UserEntity userEntity2 = FactoryUserDataTest.getUserEntity2();

        try(MockedStatic<FeignClientInterceptorImp> utilities = Mockito.mockStatic(FeignClientInterceptorImp.class)){
            utilities.when(FeignClientInterceptorImp::getBearerTokenHeader).thenReturn("Bearer token");
            Mockito.when(userServicePort.findUserByEmail(registerRequestDto.getEmail())).thenReturn(Optional.empty());
            Mockito.when(jwtHandler.extractUserName(any())).thenReturn("sebasgiraldov2@gmail.com");
            Mockito.when(userServicePort.findUserByEmail("sebasgiraldov2@gmail.com")).thenReturn(Optional.empty());

            Assertions.assertThrows(
                    NoDataFoundException.class,
                    () -> userHandler.employeeRegister(registerRequestDto, 3L)
            );
        }
    }


    @Test
    void mustBeRegisterAClient(){
        RegisterRequestDto registerRequestDto = FactoryUserDataTest.getRegisterRequestDto();
        UserRequestDto userRequestDto = FactoryUserDataTest.getUserRequestDto(4l);
        UserModel userModel = FactoryUserDataTest.getUserModel(4l, "ROLE_CLIENTE");
        UserResponseDto userResponseDto = FactoryUserDataTest.getUserResponseDto();

        Mockito.when(userServicePort.findUserByEmail(any())).thenReturn(Optional.empty());
        Mockito.when(userRequestMapper.toUserRequestDto(any())).thenReturn(userRequestDto);
        Mockito.when(userRequestMapper.toUser(any())).thenReturn(userModel);
        Mockito.when(rolServicePort.getRol(any())).thenReturn(userModel.getRolId());
        Mockito.when(userResponseMapper.toResponse(any(), any())).thenReturn(userResponseDto);

        Assertions.assertEquals(userResponseDto, userHandler.clientRegister(registerRequestDto));

        Mockito.verify(userServicePort).saveUser(any(UserModel.class));

    }

    @Test
    void throwEmailAlreadyExceptionWhenAttemoToRegisterAClient(){
        RegisterRequestDto registerRequestDto = FactoryUserDataTest.getRegisterRequestDto();
        UserEntity userEntity = FactoryUserDataTest.getUserEntity();

        Mockito.when(userServicePort.findUserByEmail(any())).thenReturn(Optional.of(userEntity));

        Assertions.assertThrows(
                EmailAlreadyTaken.class,
                ()-> userHandler.clientRegister(registerRequestDto)
        );

    }



}