package com.pragma.powerup.application.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserResponseDto {

    private Long id;
    private String name;
    private String lastname;
    private int document;
    private String phone;
    private String birthDate;
    private String email;
}
