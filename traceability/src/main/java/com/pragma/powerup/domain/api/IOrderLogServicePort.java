package com.pragma.powerup.domain.api;

import com.pragma.powerup.domain.model.OrderLogModel;

import java.util.List;

public interface IOrderLogServicePort {

    OrderLogModel saveOrderLog(OrderLogModel orderLogModel);

    OrderLogModel getOrderLog(String orderLogId);

    List<OrderLogModel> getAllOrderLogsByClient(Long clientId);

}
