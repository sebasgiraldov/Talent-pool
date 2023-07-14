package com.pragma.powerup.infrastructure.input.rest;

import com.pragma.powerup.application.dto.request.AuthenticationRequestDto;
import com.pragma.powerup.application.dto.request.RegisterRequestDto;
import com.pragma.powerup.application.dto.request.UserRequestDto;
import com.pragma.powerup.application.dto.response.JwtResponseDto;
import com.pragma.powerup.application.dto.response.ResponseClientDto;
import com.pragma.powerup.application.dto.response.ResponseDto;
import com.pragma.powerup.application.dto.response.UserResponseDto;
import com.pragma.powerup.application.handler.IUserHandler;
import com.pragma.powerup.infrastructure.exception.EmailAlreadyTaken;
import com.pragma.powerup.infrastructure.exception.NoDataFoundException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorityAuthorizationDecision;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserRestController {

        private final IUserHandler userHandler;


        @GetMapping("/email/{email}")
        public ResponseEntity<ResponseClientDto> getUserByEmail(@PathVariable String email) {
                ResponseClientDto responseDto = new ResponseClientDto();
                try {
                        userHandler.getByEmail(email);
                        responseDto.setError(false);
                        responseDto.setMessage(null);
                        responseDto.setData(userHandler.getByEmail(email));
                } catch (NoDataFoundException ex) {
                        responseDto.setError(true);
                        responseDto.setMessage("Usuario No encontrado");
                        responseDto.setData(null);
                        return new ResponseEntity<>(responseDto, HttpStatus.NOT_FOUND);
                } catch (Exception e) {
                        responseDto.setError(true);
                        responseDto.setMessage("Error interno en el servidor");
                        responseDto.setData(null);
                        return new ResponseEntity<>(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
                }

                return new ResponseEntity<>(responseDto, HttpStatus.OK);
        }


}

