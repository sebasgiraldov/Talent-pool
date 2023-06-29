package com.pragma.powerup.application.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDto {
    private Long id;
    private String name;
    private String lastname;
    private String document;
    private String phone;
    private String birthDate;
    private String email;
    private String role;
}