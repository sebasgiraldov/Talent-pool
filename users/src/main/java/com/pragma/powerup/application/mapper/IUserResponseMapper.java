package com.pragma.powerup.application.mapper;

import com.pragma.powerup.application.dto.response.RolResponseDto;
import com.pragma.powerup.application.dto.response.UserResponseDto;
import com.pragma.powerup.domain.model.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IUserResponseMapper {

    @Mapping(target = "rolId.name", source = "rolResponseDto.name")
    @Mapping(target = "rolId.description", source = "rolResponseDto.description")
    @Mapping(target = "name", source = "userModel.name")
    UserResponseDto toResponse(UserModel userModel, RolResponseDto rolResponseDto);
}
