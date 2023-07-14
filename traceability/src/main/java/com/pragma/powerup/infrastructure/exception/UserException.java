package com.pragma.powerup.infrastructure.exception;

public class UserException extends RuntimeException{

    public UserException(String mensaje){
        super(mensaje);
    }

}