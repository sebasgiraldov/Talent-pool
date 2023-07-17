package com.pragma.powerup.application.mapper;

import com.pragma.powerup.application.dto.request.OrderLogRequestDto;
import com.pragma.powerup.application.dto.response.OrderLogResponseDto;
import com.pragma.powerup.domain.model.OrderLogModel;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-17T17:54:30-0500",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.jar, environment: Java 17.0.7 (Oracle Corporation)"
)
@Component
public class IOrderLogRequestMapperImpl implements IOrderLogRequestMapper {

    @Override
    public OrderLogModel toOrderLog(OrderLogRequestDto orderLogRequestDto) {
        if ( orderLogRequestDto == null ) {
            return null;
        }

        OrderLogModel orderLogModel = new OrderLogModel();

        orderLogModel.setState( orderLogRequestDto.getState() );
        orderLogModel.setClientId( orderLogRequestDto.getClientId() );
        orderLogModel.setOrderId( orderLogRequestDto.getOrderId() );
        orderLogModel.setRestaurantId( orderLogRequestDto.getRestaurantId() );
        orderLogModel.setEmployeeId( orderLogRequestDto.getEmployeeId() );
        orderLogModel.setDate( orderLogRequestDto.getDate() );

        return orderLogModel;
    }

    @Override
    public OrderLogResponseDto toDto(OrderLogModel orderLogModel) {
        if ( orderLogModel == null ) {
            return null;
        }

        OrderLogResponseDto orderLogResponseDto = new OrderLogResponseDto();

        orderLogResponseDto.setClientId( orderLogModel.getClientId() );
        orderLogResponseDto.setOrderId( orderLogModel.getOrderId() );
        orderLogResponseDto.setRestaurantId( orderLogModel.getRestaurantId() );
        orderLogResponseDto.setEmployeeId( orderLogModel.getEmployeeId() );
        orderLogResponseDto.setDate( orderLogModel.getDate() );
        orderLogResponseDto.setState( orderLogModel.getState() );

        return orderLogResponseDto;
    }
}
