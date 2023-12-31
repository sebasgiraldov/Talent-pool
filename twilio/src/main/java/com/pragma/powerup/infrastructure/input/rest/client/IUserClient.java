package com.pragma.powerup.infrastructure.input.rest.client;

import com.pragma.powerup.application.dto.response.ResponseClientDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "users", path = "/api/v1/user", url = "http://localhost:8095")
public interface IUserClient {
    @GetMapping("/{id}")
    public ResponseEntity<ResponseClientDto> getUserById(@PathVariable Long id);

    @GetMapping("/email/{email}")
    public ResponseEntity<ResponseClientDto> getUserByEmail(@PathVariable String email);
}
