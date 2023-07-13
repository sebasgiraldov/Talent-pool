package com.pragma.powerup.application.handler.impl.Factory;

import com.pragma.powerup.application.dto.request.RegisterRequestDto;
import com.pragma.powerup.application.dto.request.UserRequestDto;
import com.pragma.powerup.application.dto.response.ResponseDto;
import com.pragma.powerup.application.dto.response.RolResponseDto;
import com.pragma.powerup.application.dto.response.UserResponseDto;
import com.pragma.powerup.domain.model.RolModel;
import com.pragma.powerup.domain.model.UserModel;
import com.pragma.powerup.infrastructure.out.jpa.entity.RolEntity;
import com.pragma.powerup.infrastructure.out.jpa.entity.UserEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class FactoryUserDataTest {
    public static UserRequestDto getUserRequestDto(Long rolId) {
        UserRequestDto userRequestDto = new UserRequestDto();

        userRequestDto.setName("Juan Sebastian");
        userRequestDto.setLastName("Giraldo");
        userRequestDto.setIdNumber("1193078576");
        userRequestDto.setPhone("+573148022302");
        userRequestDto.setEmail("sebasgiraldov@gmail.com");
        userRequestDto.setPassword("1234");
        userRequestDto.setRolId(rolId);

        return userRequestDto;
    }

    public static UserResponseDto getUserResponseDto() {
        UserResponseDto userResponseDto = new UserResponseDto();

        userResponseDto.setName("Juan Sebastian");
        userResponseDto.setLastName("Giraldo");
        userResponseDto.setIdNumber("1193078576");
        userResponseDto.setPhone("+573148022302");
        userResponseDto.setEmail("sebasgiraldov@gmail.com");
        userResponseDto.setRolId(getRolResponseDto());

        return userResponseDto;
    }

    public static UserModel getUserModel(Long rolId, String rolName) {
        UserModel userModel = new UserModel();

        userModel.setId(1L);
        userModel.setName("Juan Sebastian");
        userModel.setLastName("Giraldo");
        userModel.setIdNumber("1193078576");
        userModel.setPhone("+573148022302");
        userModel.setEmail("sebasgiraldov@gmail.com");
        userModel.setPassword("1234");
        userModel.setRolId(getRolModel(rolId, rolName));

        return userModel;
    }

    public static UserEntity getUserEntity() {
        UserEntity userEntity = new UserEntity();

        userEntity.setId(1L);
        userEntity.setName("Juan Sebastian");
        userEntity.setLastName("Giraldo");
        userEntity.setIdNumber("1193078576");
        userEntity.setPhone("+573148022302");
        userEntity.setEmail("sebasgiraldov@gmail.com");
        userEntity.setPassword("1234");
        userEntity.setRolId(getRolEntity());

        return userEntity;
    }

    public static UserEntity getUserEntity2() {
        UserEntity userEntity = new UserEntity();

        userEntity.setId(1L);
        userEntity.setName("Juan Sebastian");
        userEntity.setLastName("Giraldo");
        userEntity.setIdNumber("1193078576");
        userEntity.setPhone("+573148022302");
        userEntity.setEmail("sebasgiraldov2@gmail.com");
        userEntity.setPassword("1234");
        userEntity.setRolId(getRolEntity());

        return userEntity;
    }

    public static RolModel getRolModel(Long rolId, String roleName) {
        RolModel rolModel = new RolModel();

        rolModel.setId(rolId);
        rolModel.setName(roleName);
        rolModel.setDescription("descripcion");

        return rolModel;
    }

    public static RolEntity getRolEntity() {
        RolEntity rolEntity = new RolEntity();

        rolEntity.setId(1L);
        rolEntity.setName("ROLE_ADMINISTRADOR");
        rolEntity.setDescription("Administrador");

        return rolEntity;
    }

    public static RolResponseDto getRolResponseDto() {
        RolResponseDto rolResponseDto = new RolResponseDto();

        rolResponseDto.setName("ROLE_ADMINISTRADOR");
        rolResponseDto.setDescription("Administrador");

        return rolResponseDto;
    }

    public static ResponseDto getResponseDto(Object data, boolean error, String message) {
        ResponseDto responseDto = new ResponseDto();

        responseDto.setError(error);
        responseDto.setData(data);
        responseDto.setMessage(message);

        return responseDto;
    }

    public static RegisterRequestDto getRegisterRequestDto() {
        RegisterRequestDto registerRequestDto = new RegisterRequestDto();

        registerRequestDto.setName("Juan Sebastian");
        registerRequestDto.setLastName("Giraldo");
        registerRequestDto.setIdNumber("1193078576");
        registerRequestDto.setPhone("+573148022302");
        registerRequestDto.setEmail("sebasgiraldov@gmail.com");
        registerRequestDto.setPassword("1234");

        return registerRequestDto;
    }

    public static ResponseDto getResponseClientDto() {
        ResponseDto responseClientDto = new ResponseDto();

        responseClientDto.setMessage("");
        responseClientDto.setError(false);
        responseClientDto.setData(getUserRequestDto());

        return responseClientDto;
    }

    public static ResponseEntity<ResponseDto> getResponseEntity() {
        ResponseDto responseClientDto = getResponseClientDto();
        return new ResponseEntity<>(responseClientDto, HttpStatus.FOUND);
    }

    public static UserResponseDto getUserRequestDto() {
        UserResponseDto userRequestDto = new UserResponseDto();

        userRequestDto.setName("Juan Sebastian");
        userRequestDto.setLastName("Giraldo");
        userRequestDto.setIdNumber("1193078576");
        userRequestDto.setPhone("+573148022302");
        userRequestDto.setEmail("sebasgiraldov@gmail.com");

        return userRequestDto;
    }


}
