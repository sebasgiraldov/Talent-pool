package com.pragma.powerup.application.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class OrderLogResponseDto {
    private Long clientId;
    private Long orderId;
    private Long restaurantId;
    private Long employeeId;
    private Date date;
    private String state;
}
