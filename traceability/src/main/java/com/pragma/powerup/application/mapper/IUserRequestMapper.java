package com.pragma.powerup.application.mapper;

import com.pragma.powerup.application.dto.request.RegisterRequestDto;
import com.pragma.powerup.application.dto.request.UserRequestDto;
import com.pragma.powerup.application.dto.response.UserResponseDto;
import com.pragma.powerup.domain.model.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IUserRequestMapper {
    @Mapping(source = "userRequestDto.rolId", target = "rolId.id")
    UserModel toUser(UserRequestDto userRequestDto);
    UserResponseDto toDto(UserModel user);
    UserRequestDto toUserRequestDto(RegisterRequestDto registerRequestDto);

}
