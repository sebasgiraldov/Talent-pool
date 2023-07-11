package com.pragma.powerup.application.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDto {

    private Long id;
    private String name;
    private String last_name;
    private String cellphone;
    private String email;
    private String password;
    private RoleRequestDto role;
}
