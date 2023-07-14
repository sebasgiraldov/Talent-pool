package com.pragma.powerup.application.mapper;

import com.pragma.powerup.application.dto.response.AllOrderLogResponseDto;
import com.pragma.powerup.application.dto.response.OrderLogResponseDto;
import com.pragma.powerup.domain.model.OrderLogModel;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-14T16:25:57-0500",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.jar, environment: Java 17.0.7 (Oracle Corporation)"
)
@Component
public class IOrderLogResponseMapperImpl implements IOrderLogResponseMapper {

    @Override
    public OrderLogResponseDto toResponse(OrderLogModel orderLogModel) {
        if ( orderLogModel == null ) {
            return null;
        }

        OrderLogResponseDto orderLogResponseDto = new OrderLogResponseDto();

        orderLogResponseDto.setClientId( orderLogModel.getClientId() );
        orderLogResponseDto.setOrderId( orderLogModel.getOrderId() );
        orderLogResponseDto.setDate( orderLogModel.getDate() );
        orderLogResponseDto.setState( orderLogModel.getState() );

        return orderLogResponseDto;
    }

    @Override
    public List<AllOrderLogResponseDto> toResponseList(List<OrderLogModel> orderLogModels) {
        if ( orderLogModels == null ) {
            return null;
        }

        List<AllOrderLogResponseDto> list = new ArrayList<AllOrderLogResponseDto>( orderLogModels.size() );
        for ( OrderLogModel orderLogModel : orderLogModels ) {
            list.add( orderLogModelToAllOrderLogResponseDto( orderLogModel ) );
        }

        return list;
    }

    protected AllOrderLogResponseDto orderLogModelToAllOrderLogResponseDto(OrderLogModel orderLogModel) {
        if ( orderLogModel == null ) {
            return null;
        }

        AllOrderLogResponseDto allOrderLogResponseDto = new AllOrderLogResponseDto();

        allOrderLogResponseDto.setOrderId( orderLogModel.getOrderId() );
        allOrderLogResponseDto.setDate( orderLogModel.getDate() );
        allOrderLogResponseDto.setState( orderLogModel.getState() );

        return allOrderLogResponseDto;
    }
}
