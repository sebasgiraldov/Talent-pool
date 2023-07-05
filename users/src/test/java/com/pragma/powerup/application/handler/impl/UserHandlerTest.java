package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.UserRequestDto;
import com.pragma.powerup.application.mapper.IUserRequestMapper;
import com.pragma.powerup.application.mapper.IUserResponseMapper;
import com.pragma.powerup.domain.api.IUserServicePort;
import com.pragma.powerup.domain.model.UserModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
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

    @Test
    void saveOwner() {
        UserRequestDto userRequestDto = new UserRequestDto();
        userRequestDto.setName("Sebastian");
        userRequestDto.setLastname("G");
        userRequestDto.setDocument(1193078576);
        userRequestDto.setPhone("573149022302");
        userRequestDto.setEmail("sebas@gmail.com");
        userRequestDto.setPass("hola123");
        UserModel user = new UserModel(10L,"Sebastian", "G", 1193078576, "+573149022302", "1999-30-04", "sebas@gmail.com", "hola123", "OWNER");
        Mockito.when(userRequestMapper.toUser(any())).thenReturn(user);
        UserModel user1 = userRequestMapper.toUser(userRequestDto);
        Mockito.verify(userServicePort).saveOwner((user1));
    }

}