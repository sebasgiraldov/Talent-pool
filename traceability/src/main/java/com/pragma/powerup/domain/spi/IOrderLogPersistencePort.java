package com.pragma.powerup.domain.spi;

import com.pragma.powerup.domain.model.OrderLogModel;

import java.util.List;

public interface IOrderLogPersistencePort {

    OrderLogModel saveOrderLog(OrderLogModel orderLogModel);

    OrderLogModel getOrderLog(String orderLogId);
    List<OrderLogModel> getAllOrderLogs();

    List<OrderLogModel> getAllOrderLogsByClient(Long clientId);

    List<OrderLogModel> getAllOrderLogsByOrder(Long orderId);

    List<OrderLogModel> getAllOrderLogsByRestaurant(Long restaurantId);

    List<OrderLogModel> getAllOrderLogsByEmployee(Long employeeId);
}
