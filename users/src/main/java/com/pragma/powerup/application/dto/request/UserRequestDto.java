package com.pragma.powerup.application.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UserRequestDto {

    private String name;
    private String lastname;
    private int document;
    private String phone;
    private String birthDate;
    private String email;
    private String pass;

}
