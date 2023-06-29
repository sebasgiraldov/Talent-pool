package com.pragma.powerup.infrastructure.input.rest;

import com.pragma.powerup.application.dto.request.UserRequestDto;
import com.pragma.powerup.application.dto.response.UserResponseDto;
import com.pragma.powerup.application.handler.IUserHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserRestController {

        private final IUserHandler userHandler;

        @Operation(summary = "Add a new user")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "201", description = "User created", content = @Content),
                        @ApiResponse(responseCode = "409", description = "User already exists", content = @Content)
        })
        @PostMapping("/")
        public ResponseEntity<Void> saveUser(@RequestBody UserRequestDto userRequestDto) {
                userHandler.saveUser(userRequestDto);
                return new ResponseEntity<>(HttpStatus.CREATED);
        }

        @Operation(summary = "Add a new owner")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "201", description = "Owner created", content = @Content),
                        @ApiResponse(responseCode = "409", description = "Owner already exists", content = @Content)
        })
        @PostMapping("/owner")
        public ResponseEntity<Void> saveOwner(@RequestBody UserRequestDto userRequestDto) {
                userHandler.saveOwner(userRequestDto);
                return new ResponseEntity<>(HttpStatus.CREATED);
        }

        @Operation(summary = "Get all users")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "All users returned", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = UserResponseDto.class)))),
                        @ApiResponse(responseCode = "404", description = "No data found", content = @Content)
        })
        @GetMapping("/")
        public ResponseEntity<List<UserResponseDto>> getAllUsers() {
                return ResponseEntity.ok(userHandler.getAllUsers());
        }

        @Operation(summary = "Get an user by document")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "A users is returned", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = UserResponseDto.class)))),
                        @ApiResponse(responseCode = "404", description = "No data found", content = @Content)
        })
        @GetMapping("/{document}")
        public ResponseEntity<UserResponseDto> getUser(@PathVariable(name = "document") int document) {
                return ResponseEntity.ok(userHandler.getUser(document));
        }

        @Operation(summary = "Update a user")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "201", description = "Object updated", content = @Content),
                        @ApiResponse(responseCode = "409", description = "Object no exists", content = @Content)
        })
        @PutMapping("/")
        public ResponseEntity<Void> updateUser(@RequestBody UserRequestDto userRequestDto) {
                userHandler.updateUser(userRequestDto);
                return ResponseEntity.noContent().build();
        }

        @Operation(summary = "Delete a user")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "201", description = "Object deleted", content = @Content),
                        @ApiResponse(responseCode = "409", description = "Object no exists", content = @Content)
        })
        @DeleteMapping("/{document}")
        public ResponseEntity<Void> deleteUser(@PathVariable Integer document) {
                userHandler.deleteUser(document);
                return ResponseEntity.noContent().build();
        }

}
