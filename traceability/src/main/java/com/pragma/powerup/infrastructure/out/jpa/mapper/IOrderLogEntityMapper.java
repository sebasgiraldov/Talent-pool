package com.pragma.powerup.infrastructure.out.jpa.mapper;

import com.pragma.powerup.domain.model.OrderLogModel;
import com.pragma.powerup.infrastructure.out.jpa.entity.OrderLogEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE
)
public interface IOrderLogEntityMapper {

    OrderLogEntity toEntity(OrderLogModel orderLogModel);
    OrderLogModel toOrderLogModel(OrderLogEntity orderLogEntity);
    List<OrderLogModel> toOrderLogModelList(List<OrderLogEntity> orderLogEntityList);

}
