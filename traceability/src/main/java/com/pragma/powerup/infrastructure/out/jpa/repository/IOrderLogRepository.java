package com.pragma.powerup.infrastructure.out.jpa.repository;

import com.pragma.powerup.infrastructure.out.jpa.entity.OrderLogEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface IOrderLogRepository extends MongoRepository<OrderLogEntity, String> {
    List<OrderLogEntity> findByClientId(Long clientId);
    List<OrderLogEntity> findByEmployeeId(Long employeeId);
    List<OrderLogEntity> findByOrderId(Long orderId);
    List<OrderLogEntity> findByrestaurantId(Long restaurantId);



}
