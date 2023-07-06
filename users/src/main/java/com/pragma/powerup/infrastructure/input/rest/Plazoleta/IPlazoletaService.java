package com.pragma.powerup.infrastructure.input.rest.Plazoleta;

import com.pragma.powerup.application.dto.request.RestaurantEmployeeRequestDto;
import com.pragma.powerup.application.dto.response.ResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "plazoleta", path = "/api/v1/restaurantemployee", url = "http://localhost:8094")
public interface IPlazoletaService {
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> saveRestaurantEmployee(@RequestBody RestaurantEmployeeRequestDto restaurantEmployeeRequestDto);

}
