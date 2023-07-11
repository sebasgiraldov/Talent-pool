package com.pragma.powerup.application.handler.impl;

import com.pragma.powerup.application.dto.request.OrderDishRequestDto;
import com.pragma.powerup.application.dto.request.OrderRequestDto;
import com.pragma.powerup.application.dto.request.UserRequestDto;
import com.pragma.powerup.application.dto.response.OrderDishResponseDto;
import com.pragma.powerup.application.dto.response.OrderResponseDto;
import com.pragma.powerup.application.dto.response.OrderStateResponseDto;
import com.pragma.powerup.application.handler.IOrderDishHandler;
import com.pragma.powerup.application.handler.IOrderHandler;
import com.pragma.powerup.application.mapper.IOrderDishResponseMapper;
import com.pragma.powerup.application.mapper.IOrderRequestMapper;
import com.pragma.powerup.application.mapper.IOrderResponseMapper;
import com.pragma.powerup.application.mapper.IUserRequestMapper;
import com.pragma.powerup.domain.api.*;
import com.pragma.powerup.domain.model.OrderModel;
import com.pragma.powerup.domain.model.OrderState;
import com.pragma.powerup.domain.model.RestaurantEmployeeModel;
import com.pragma.powerup.domain.model.RestaurantModel;
import com.pragma.powerup.infrastructure.configuration.FeignClientInterceptorImp;
import com.pragma.powerup.infrastructure.exception.DishNotFoundInRestaurantException;
import com.pragma.powerup.infrastructure.input.rest.client.IUserClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderHandler implements IOrderHandler {

    private final IOrderServicePort orderServicePort;
    private final IOrderRequestMapper orderRequestMapper;
    private final IOrderResponseMapper orderResponseMapper;
    private final IOrderDishResponseMapper orderDishResponseMapper;
    private final IOrderDishServicePort orderDishServicePort;
    private final IRestaurantServicePort restaurantServicePort;
    private final IOrderDishHandler orderDishHandler;
    private final IRestaurantEmployeeServicePort restaurantEmployeeServicePort;
    private final JwtHandler jwtHandler;
    private final IUserClient userClient;
    private final IUserRequestMapper userRequestMapper;
    private final IDishServicePort dishServicePort;
    @Override
    public OrderResponseDto createOrder(OrderRequestDto orderRequestDto) {
        RestaurantModel restaurantModel = restaurantServicePort.getRestaurant(orderRequestDto.getRestaurantId());
        String tokenHeader = FeignClientInterceptorImp.getBearerTokenHeader();
        String[] splited = tokenHeader.split("\\s+");
        String email = jwtHandler.extractUserName(splited[1]);
        UserRequestDto userRequestDto = userClient.getUserByEmail(email).getBody().getData();
        List<OrderState> orderStateList = Arrays.asList(OrderState.EN_PREPARACION, OrderState.PENDIENTE, OrderState.LISTO);

        orderServicePort.getAllOrdersByUserIdOrderStateIn(userRequestDto.getId(), orderStateList);

        OrderModel orderModel = new OrderModel();
        orderModel.setClientId(userRequestMapper.toUser(userRequestDto));
        orderModel.setDate(new Date());
        orderModel.setOrderState(OrderState.PENDIENTE);
        orderModel.setChefId(null);
        orderModel.setRestaurantId(restaurantModel);

        OrderModel orderModelResponse = orderServicePort.createOrder(orderModel);

        List<OrderDishRequestDto> orderDishRequestDtoList = orderRequestDto.getOrders();

        List<OrderDishResponseDto> orderDishResponseDtoList =
                orderDishRequestDtoList.stream()
                        .map(orderDish -> {

                            if (!Objects.equals(dishServicePort.getDish(orderDish.getDishId()).getRestaurantId().getId(), restaurantModel.getId())) {
                                throw new DishNotFoundInRestaurantException();
                            }

                            return orderDishHandler.createOrderDish(orderDish, orderModelResponse.getId());
                        }).collect(Collectors.toList());

        return orderResponseMapper.toResponse(orderModelResponse, orderDishResponseDtoList);
    }

    @Override
    public OrderResponseDto getOrder(Long orderId) {
        return null;
    }

    @Override
    public List<OrderStateResponseDto> getAllOrdersByOrderState(OrderState orderState) {
        String tokenHeader = FeignClientInterceptorImp.getBearerTokenHeader();
        String splited[] = tokenHeader.split("\\s+");
        String email = jwtHandler.extractUserName(splited[1]);
        UserRequestDto userRequestDto = userClient.getUserByEmail(email).getBody().getData();

        RestaurantEmployeeModel restaurantEmployeeModel = restaurantEmployeeServicePort.getRestaurantByEmployeeId(userRequestDto.getId());

        return orderResponseMapper.toReponseList(orderServicePort.getAllOrdersByOrderState(orderState, restaurantEmployeeModel.getRestaurantId().getId()), restaurantServicePort.getAllRestaurants(), orderDishServicePort.getAllOrderDish());
    }

    @Override
    public OrderResponseDto asignAnOrder(Long orderId) {
        return null;
    }
}
