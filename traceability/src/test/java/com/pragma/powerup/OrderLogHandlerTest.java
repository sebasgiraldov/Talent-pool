package com.pragma.powerup;

import com.pragma.powerup.Factory.FactoryOrderLogDataTest;
import com.pragma.powerup.application.dto.request.OrderLogRequestDto;
import com.pragma.powerup.application.dto.response.AllOrderLogResponseDto;
import com.pragma.powerup.application.dto.response.OrderDurationResponseDto;
import com.pragma.powerup.application.dto.response.OrderLogResponseDto;
import com.pragma.powerup.application.handler.impl.JwtHandler;
import com.pragma.powerup.application.handler.impl.OrderLogHandler;
import com.pragma.powerup.application.mapper.IOrderLogRequestMapper;
import com.pragma.powerup.application.mapper.IOrderLogResponseMapper;
import com.pragma.powerup.domain.api.IOrderLogServicePort;
import com.pragma.powerup.domain.model.OrderLogModel;
import com.pragma.powerup.infrastructure.input.rest.client.IUserClient;
import com.pragma.powerup.infrastructure.out.jpa.repository.IOrderLogRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
public class OrderLogHandlerTest {

    @InjectMocks
    OrderLogHandler orderLogHandler;
    @Mock
    IOrderLogServicePort orderLogServicePort;
    @Mock
    IOrderLogRequestMapper orderLogRequestMapper;
    @Mock
    IOrderLogResponseMapper orderLogResponseMapper;
    @Mock
    IOrderLogRepository orderLogRepository;


    @Test
    void mustSaveAOrderLog(){
        OrderLogRequestDto orderLogRequestDto = FactoryOrderLogDataTest.getOrderLogRequestDto();
        OrderLogModel orderLogModel = FactoryOrderLogDataTest.getOrderLogModel();
        OrderLogResponseDto orderLogResponseDto = FactoryOrderLogDataTest.getOrderLogResponseDto();

        Mockito.when(orderLogRequestMapper.toOrderLog(any())).thenReturn(orderLogModel);
        Mockito.when(orderLogResponseMapper.toResponse(any())).thenReturn(orderLogResponseDto);

        Assertions.assertEquals(orderLogResponseDto, orderLogHandler.saveOrderLog(orderLogRequestDto));

    }

    @Test
    void mustListAllOrderLogsByClient(){
        OrderLogModel orderLogModel = FactoryOrderLogDataTest.getOrderLogModel();
        List<AllOrderLogResponseDto> orderLogResponseDtoList = new ArrayList<>();
        Mockito.when(orderLogServicePort.getAllOrderLogsByClient(1L)).thenReturn(List.of(orderLogModel));
        Assertions.assertEquals(orderLogResponseDtoList, orderLogHandler.getAllOrderLogsByClient(1L));
    }

    @Test
    void mustListAllOrderLogsEfficiency(){
        List<OrderDurationResponseDto> orderDurationResponseDtos = new ArrayList<>();
        Assertions.assertEquals(orderDurationResponseDtos, orderLogHandler.getAllOrderLogsEfficiency(1L));
    }


}
