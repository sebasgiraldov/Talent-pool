package com.pragma.powerup.application.handler;

import com.pragma.powerup.application.dto.request.OrderLogRequestDto;
import com.pragma.powerup.application.dto.response.AllOrderLogResponseDto;
import com.pragma.powerup.application.dto.response.EmployeeOrderDurationResponseDto;
import com.pragma.powerup.application.dto.response.OrderLogResponseDto;
import com.pragma.powerup.application.dto.response.OrderDurationResponseDto;

import java.util.List;

public interface IOrderLogHandler {

    OrderLogResponseDto saveOrderLog(OrderLogRequestDto orderLogRequestDto);

    List<AllOrderLogResponseDto> getAllOrderLogsByClient(Long clientId);

    OrderLogResponseDto getOrderLog(String id);

    List<OrderDurationResponseDto> getAllOrderLogsEfficiency(Long restaurantId);
    List<EmployeeOrderDurationResponseDto> getAllOrderLogsEmployees(Long restaurantId);

    OrderDurationResponseDto getOrderDuration(Long orderId);

}
