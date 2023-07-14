package com.pragma.powerup.application.mapper;

import com.pragma.powerup.application.dto.request.OrderLogRequestDto;
import com.pragma.powerup.application.dto.response.OrderLogResponseDto;
import com.pragma.powerup.domain.model.OrderLogModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IOrderLogRequestMapper {
    OrderLogModel toOrderLog(OrderLogRequestDto orderLogRequestDto);
    OrderLogResponseDto toDto(OrderLogModel orderLogModel);
}
