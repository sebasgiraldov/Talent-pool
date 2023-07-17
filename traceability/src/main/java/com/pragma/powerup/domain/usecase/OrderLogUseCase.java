package com.pragma.powerup.domain.usecase;

import com.pragma.powerup.domain.api.IOrderLogServicePort;
import com.pragma.powerup.domain.model.OrderLogModel;
import com.pragma.powerup.domain.spi.IOrderLogPersistencePort;

import java.util.List;

public class OrderLogUseCase implements IOrderLogServicePort {

    private final IOrderLogPersistencePort orderLogPersistencePort;
    public OrderLogUseCase(IOrderLogPersistencePort orderLogPersistencePort) {
        this.orderLogPersistencePort = orderLogPersistencePort;
    }

    @Override
    public OrderLogModel saveOrderLog(OrderLogModel orderLogModel) {
        return orderLogPersistencePort.saveOrderLog(orderLogModel);
    }

    @Override
    public OrderLogModel getOrderLog(String orderLogId) {
        return orderLogPersistencePort.getOrderLog(orderLogId);
    }

    @Override
    public List<OrderLogModel> getAllOrderLogs(){
        return orderLogPersistencePort.getAllOrderLogs();
    }

    @Override
    public List<OrderLogModel> getAllOrderLogsByClient(Long clientId) {
        return orderLogPersistencePort.getAllOrderLogsByClient(clientId);
    }

    @Override
    public List<OrderLogModel> getAllOrderLogsByOrder(Long orderId) {
        return orderLogPersistencePort.getAllOrderLogsByOrder(orderId);
    }

    @Override
    public List<OrderLogModel> getAllOrderLogsByEmployee(Long employeeId) {
        return orderLogPersistencePort.getAllOrderLogsByEmployee(employeeId);
    }

    @Override
    public List<OrderLogModel> getAllOrderLogsByRestaurant(Long restaurantId) {
        return orderLogPersistencePort.getAllOrderLogsByRestaurant(restaurantId);
    }
}
