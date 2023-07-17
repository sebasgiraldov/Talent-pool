package com.pragma.powerup.application.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AllOrderLogResponseDto {
    private Long orderId;
    private Long restaurantId;
    private Date date;
    private String state;
}
