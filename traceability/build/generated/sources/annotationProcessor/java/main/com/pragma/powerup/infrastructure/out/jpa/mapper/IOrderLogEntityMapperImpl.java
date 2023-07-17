package com.pragma.powerup.infrastructure.out.jpa.mapper;

import com.pragma.powerup.domain.model.OrderLogModel;
import com.pragma.powerup.infrastructure.out.jpa.entity.OrderLogEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-17T17:54:30-0500",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.jar, environment: Java 17.0.7 (Oracle Corporation)"
)
@Component
public class IOrderLogEntityMapperImpl implements IOrderLogEntityMapper {

    @Override
    public OrderLogEntity toEntity(OrderLogModel orderLogModel) {
        if ( orderLogModel == null ) {
            return null;
        }

        OrderLogEntity orderLogEntity = new OrderLogEntity();

        orderLogEntity.setId( orderLogModel.getId() );
        orderLogEntity.setClientId( orderLogModel.getClientId() );
        orderLogEntity.setOrderId( orderLogModel.getOrderId() );
        orderLogEntity.setRestaurantId( orderLogModel.getRestaurantId() );
        orderLogEntity.setEmployeeId( orderLogModel.getEmployeeId() );
        orderLogEntity.setDate( orderLogModel.getDate() );
        orderLogEntity.setState( orderLogModel.getState() );

        return orderLogEntity;
    }

    @Override
    public OrderLogModel toOrderLogModel(OrderLogEntity orderLogEntity) {
        if ( orderLogEntity == null ) {
            return null;
        }

        OrderLogModel orderLogModel = new OrderLogModel();

        orderLogModel.setId( orderLogEntity.getId() );
        orderLogModel.setState( orderLogEntity.getState() );
        orderLogModel.setClientId( orderLogEntity.getClientId() );
        orderLogModel.setOrderId( orderLogEntity.getOrderId() );
        orderLogModel.setRestaurantId( orderLogEntity.getRestaurantId() );
        orderLogModel.setEmployeeId( orderLogEntity.getEmployeeId() );
        orderLogModel.setDate( orderLogEntity.getDate() );

        return orderLogModel;
    }

    @Override
    public List<OrderLogModel> toOrderLogModelList(List<OrderLogEntity> orderLogEntityList) {
        if ( orderLogEntityList == null ) {
            return null;
        }

        List<OrderLogModel> list = new ArrayList<OrderLogModel>( orderLogEntityList.size() );
        for ( OrderLogEntity orderLogEntity : orderLogEntityList ) {
            list.add( toOrderLogModel( orderLogEntity ) );
        }

        return list;
    }
}
