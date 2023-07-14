package com.pragma.powerup.application.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserResponseDto {
    private String id;
    private String name;
    private String lastName;
    private String idNumber;
    private String phone;
    private String email;
    private RolResponseDto rolId;
}
