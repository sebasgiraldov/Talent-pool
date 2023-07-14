package com.pragma.powerup.domain.spi;

import com.pragma.powerup.domain.model.OrderLogModel;

import java.util.List;

public interface IOrderLogPersistencePort {

    OrderLogModel saveOrderLog(OrderLogModel orderLogModel);

    OrderLogModel getOrderLog(String orderLogId);

    List<OrderLogModel> getAllOrderLogsByClient(Long clientId);


}
