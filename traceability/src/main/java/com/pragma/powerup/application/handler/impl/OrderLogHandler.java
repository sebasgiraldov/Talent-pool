package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.OrderLogRequestDto;
import com.pragma.powerup.application.dto.response.AllOrderLogResponseDto;
import com.pragma.powerup.application.dto.response.OrderLogResponseDto;
import com.pragma.powerup.application.handler.IOrderLogHandler;
import com.pragma.powerup.application.mapper.IOrderLogRequestMapper;
import com.pragma.powerup.application.mapper.IOrderLogResponseMapper;
import com.pragma.powerup.domain.api.IOrderLogServicePort;
import com.pragma.powerup.domain.model.OrderLogModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderLogHandler implements IOrderLogHandler {

    private final IOrderLogServicePort orderLogServicePort;
    private final IOrderLogRequestMapper orderLogRequestMapper;
    private final IOrderLogResponseMapper orderLogResponseMapper;

    @Override
    public OrderLogResponseDto saveOrderLog(OrderLogRequestDto orderLogRequestDto) {
        OrderLogModel orderLogModel = orderLogRequestMapper.toOrderLog(orderLogRequestDto);
        return orderLogResponseMapper.toResponse(orderLogServicePort.saveOrderLog(orderLogModel));
    }

    @Override
    public List<AllOrderLogResponseDto> getAllOrderLogsByClient(Long clientId) {
        return orderLogResponseMapper.toResponseList(orderLogServicePort.getAllOrderLogsByClient(clientId));
    }

    @Override
    public OrderLogResponseDto getOrderLog(String orderLogId) {
        return orderLogResponseMapper.toResponse(orderLogServicePort.getOrderLog(orderLogId));
    }
}
