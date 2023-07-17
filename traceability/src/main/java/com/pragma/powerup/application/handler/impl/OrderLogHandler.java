package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.OrderLogRequestDto;
import com.pragma.powerup.application.dto.request.UserRequestDto;
import com.pragma.powerup.application.dto.response.AllOrderLogResponseDto;
import com.pragma.powerup.application.dto.response.EmployeeOrderDurationResponseDto;
import com.pragma.powerup.application.dto.response.OrderDurationResponseDto;
import com.pragma.powerup.application.dto.response.OrderLogResponseDto;
import com.pragma.powerup.application.handler.IJwtHandler;
import com.pragma.powerup.application.handler.IOrderLogHandler;
import com.pragma.powerup.application.mapper.IOrderLogRequestMapper;
import com.pragma.powerup.application.mapper.IOrderLogResponseMapper;
import com.pragma.powerup.domain.api.IOrderLogServicePort;
import com.pragma.powerup.domain.model.OrderLogModel;
import com.pragma.powerup.infrastructure.configuration.FeignClientInterceptorImp;
import com.pragma.powerup.infrastructure.input.rest.client.IUserClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OrderLogHandler implements IOrderLogHandler {

    private final IOrderLogServicePort orderLogServicePort;
    private final IOrderLogRequestMapper orderLogRequestMapper;
    private final IOrderLogResponseMapper orderLogResponseMapper;
    private final IUserClient userClient;
    private final IJwtHandler jwtHandler;

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

    @Override
    public List<OrderDurationResponseDto> getAllOrderLogsEfficiency(Long restaurantId) {
        List<OrderDurationResponseDto> durations = new ArrayList<>();
        List<OrderLogModel> orders = orderLogServicePort.getAllOrderLogsByRestaurant(restaurantId);
        Map<Long, List<OrderLogModel>> ordersGroup = new HashMap<>();
        for (OrderLogModel order : orders) {
            Long orderId = order.getOrderId();
            ordersGroup.computeIfAbsent(orderId, k -> new ArrayList<>()).add(order);
        }
        for (List<OrderLogModel> group : ordersGroup.values()) {
            if (group.size() > 1){
                durations.add(calculateDuration(group));
            }
        }
        return durations;
    }

    @Override
    public List<EmployeeOrderDurationResponseDto> getAllOrderLogsEmployees(Long restaurantId) {
        List<EmployeeOrderDurationResponseDto> ranking = new ArrayList<>();
        List<OrderDurationResponseDto> traceability = getAllOrderLogsEfficiency(restaurantId);
        Map<Long, List<OrderDurationResponseDto>> ordersGroup = new HashMap<>();
        for (OrderDurationResponseDto order : traceability) {
            Long employeeId = order.getEmployeeId();
            ordersGroup.computeIfAbsent(employeeId, k -> new ArrayList<>()).add(order);
        }
        EmployeeOrderDurationResponseDto employeeRanking = new EmployeeOrderDurationResponseDto();
        for (List<OrderDurationResponseDto> group : ordersGroup.values()) {
            employeeRanking.setEmployeeId(group.get(0).getEmployeeId());
            employeeRanking.setAverage(calculateAvg(group));
            ranking.add(employeeRanking);
        }
        return ranking;
    }


    @Override
    public OrderDurationResponseDto getOrderDuration(Long orderId) {
        List<OrderLogModel> orders = orderLogServicePort.getAllOrderLogsByOrder(orderId);
        return calculateDuration(orders);
    }

    public OrderDurationResponseDto calculateDuration(List<OrderLogModel> orders){
        Long duration = 0L;
        for (int i = 0; i < orders.size() - 1; i++){
            duration += orders.get(i+1).getDate().getTime() - orders.get(i).getDate().getTime();
        }
        duration = duration/1000/60;
        return orderLogResponseMapper.toDuration(orders.get(0), duration);
    }

    public Long calculateAvg(List<OrderDurationResponseDto> orders){
        Long average = 0L;
        for (int i = 0; i < orders.size(); i++){
            average += orders.get(i).getDuration();
        }
        average = average/orders.size();
        return average;
    }
}
