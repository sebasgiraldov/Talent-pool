package com.pragma.powerup.application.mapper;

import com.pragma.powerup.application.dto.response.*;
import com.pragma.powerup.domain.model.OrderLogModel;
import com.pragma.powerup.domain.model.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IOrderLogResponseMapper {
    OrderLogResponseDto toResponse(OrderLogModel orderLogModel);
    List<AllOrderLogResponseDto> toResponseList(List<OrderLogModel> orderLogModels);
    OrderDurationResponseDto toDuration(OrderLogModel orderLogModel, Long duration);

}
