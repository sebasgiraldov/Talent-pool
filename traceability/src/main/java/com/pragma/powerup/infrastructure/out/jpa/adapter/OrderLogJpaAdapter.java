package com.pragma.powerup.infrastructure.out.jpa.adapter;

import com.pragma.powerup.domain.model.OrderLogModel;
import com.pragma.powerup.domain.spi.IOrderLogPersistencePort;
import com.pragma.powerup.infrastructure.exception.NoDataFoundException;
import com.pragma.powerup.infrastructure.out.jpa.entity.OrderLogEntity;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IOrderLogEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.IOrderLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@RequiredArgsConstructor
public class OrderLogJpaAdapter implements IOrderLogPersistencePort {

    private final IOrderLogRepository orderLogRepository;
    private final IOrderLogEntityMapper orderLogEntityMapper;
    @Override
    public OrderLogModel saveOrderLog(OrderLogModel orderLogModel) {
        OrderLogEntity orderLogEntity = orderLogRepository.save(orderLogEntityMapper.toEntity(orderLogModel));
        return orderLogEntityMapper.toOrderLogModel(orderLogEntity);
    }

    @Override
    public OrderLogModel getOrderLog(String orderLogId) {
        return orderLogEntityMapper.toOrderLogModel(orderLogRepository.findById(orderLogId).orElseThrow(NoDataFoundException::new));
    }

    @Override
    public List<OrderLogModel> getAllOrderLogs() {
        return orderLogEntityMapper.toOrderLogModelList(orderLogRepository.findAll());
    }

    @Override
    public List<OrderLogModel> getAllOrderLogsByClient(Long clientId) {
        List<OrderLogEntity> orderLogEntityList = orderLogRepository.findByClientId(clientId);

        if(orderLogEntityList.isEmpty()){
            throw new NoDataFoundException();
        }

        return orderLogEntityMapper.toOrderLogModelList(orderLogEntityList);
    }

    @Override
    public List<OrderLogModel> getAllOrderLogsByOrder(Long orderId) {
        List<OrderLogEntity> orderLogEntityList = orderLogRepository.findByOrderId(orderId);

        if(orderLogEntityList.isEmpty()){
            throw new NoDataFoundException();
        }

        return orderLogEntityMapper.toOrderLogModelList(orderLogEntityList);
    }

    @Override
    public List<OrderLogModel> getAllOrderLogsByRestaurant(Long restaurantId) {
        List<OrderLogEntity> orderLogEntityList = orderLogRepository.findByrestaurantId(restaurantId);

        if(orderLogEntityList.isEmpty()){
            throw new NoDataFoundException();
        }

        return orderLogEntityMapper.toOrderLogModelList(orderLogEntityList);
    }

    @Override
    public List<OrderLogModel> getAllOrderLogsByEmployee(Long employeeId) {
        List<OrderLogEntity> orderLogEntityList = orderLogRepository.findByEmployeeId(employeeId);

        if(orderLogEntityList.isEmpty()){
            throw new NoDataFoundException();
        }

        return orderLogEntityMapper.toOrderLogModelList(orderLogEntityList);
    }

}
