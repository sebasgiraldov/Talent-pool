package com.pragma.powerup.application.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDurationResponseDto {

    private Long clientId;
    private Long orderId;
    private Long restaurantId;
    private Long employeeId;
    private Long duration;

}
