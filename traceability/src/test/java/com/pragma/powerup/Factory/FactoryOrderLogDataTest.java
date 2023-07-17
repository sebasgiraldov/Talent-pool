package com.pragma.powerup.Factory;

import com.pragma.powerup.application.dto.request.OrderLogRequestDto;
import com.pragma.powerup.application.dto.response.AllOrderLogResponseDto;
import com.pragma.powerup.application.dto.response.EmployeeOrderDurationResponseDto;
import com.pragma.powerup.application.dto.response.OrderDurationResponseDto;
import com.pragma.powerup.application.dto.response.OrderLogResponseDto;
import com.pragma.powerup.domain.model.OrderLogModel;

import java.util.Date;

public class FactoryOrderLogDataTest {

    public static OrderLogModel getOrderLogModel(){
        OrderLogModel orderLogModel = new OrderLogModel();

        orderLogModel.setState("PENDIENTE");
        orderLogModel.setClientId(1L);
        orderLogModel.setOrderId(1L);
        orderLogModel.setRestaurantId(1L);
        orderLogModel.setEmployeeId(1L);
        orderLogModel.setDate(new Date());

        return orderLogModel;
    }

    public static OrderLogRequestDto getOrderLogRequestDto(){

        OrderLogRequestDto orderLogRequestDto = new OrderLogRequestDto();
        orderLogRequestDto.setState("PENDIENTE");
        orderLogRequestDto.setClientId(1L);
        orderLogRequestDto.setOrderId(1L);
        orderLogRequestDto.setRestaurantId(1L);
        orderLogRequestDto.setEmployeeId(1L);
        orderLogRequestDto.setDate(new Date());
        return orderLogRequestDto;


    }

    public static OrderLogResponseDto getOrderLogResponseDto(){
        OrderLogResponseDto orderLogResponseDto = new OrderLogResponseDto();
        orderLogResponseDto.setState("PENDIENTE");
        orderLogResponseDto.setClientId(1L);
        orderLogResponseDto.setOrderId(1L);
        orderLogResponseDto.setRestaurantId(1L);
        orderLogResponseDto.setEmployeeId(1L);
        orderLogResponseDto.setDate(new Date());
        return orderLogResponseDto;
    }

    public static OrderDurationResponseDto getOrderDurationResponseDto(){
        OrderDurationResponseDto orderDurationResponseDto = new OrderDurationResponseDto();
        orderDurationResponseDto.setClientId(1L);
        orderDurationResponseDto.setOrderId(1L);
        orderDurationResponseDto.setRestaurantId(1L);
        orderDurationResponseDto.setEmployeeId(1L);
        orderDurationResponseDto.setDuration(20L);
        return orderDurationResponseDto;
    }

    public static EmployeeOrderDurationResponseDto getEmployeeOrderDurationResponseDto(){
        EmployeeOrderDurationResponseDto employeeOrderDurationResponseDto = new EmployeeOrderDurationResponseDto();
        employeeOrderDurationResponseDto.setAverage(10L);
        employeeOrderDurationResponseDto.setEmployeeId(1L);
        return employeeOrderDurationResponseDto;
    }


    public static AllOrderLogResponseDto getAllOrderLogResponseDto(){
        AllOrderLogResponseDto allOrderLogResponseDto = new AllOrderLogResponseDto();
        allOrderLogResponseDto.setOrderId(1l);
        allOrderLogResponseDto.setDate(new Date());
        allOrderLogResponseDto.setState("PENDIENTE");
        allOrderLogResponseDto.setRestaurantId(1L);
        return allOrderLogResponseDto;
    }

}
