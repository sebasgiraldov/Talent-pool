package com.pragma.powerup.application.mapper;

import com.pragma.powerup.application.dto.response.RolResponseDto;
import com.pragma.powerup.application.dto.response.UserResponseDto;
import com.pragma.powerup.domain.model.UserModel;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-30T15:54:33-0500",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.jar, environment: Java 17.0.7 (Oracle Corporation)"
)
@Component
public class IUserResponseMapperImpl implements IUserResponseMapper {

    @Override
    public UserResponseDto toResponse(UserModel userModel, RolResponseDto rolResponseDto) {
        if ( userModel == null && rolResponseDto == null ) {
            return null;
        }

        UserResponseDto userResponseDto = new UserResponseDto();

        if ( userModel != null ) {
            userResponseDto.setName( userModel.getName() );
            userResponseDto.setId( userModel.getId() );
            userResponseDto.setLastName( userModel.getLastName() );
            userResponseDto.setIdNumber( userModel.getIdNumber() );
            userResponseDto.setPhone( userModel.getPhone() );
            userResponseDto.setEmail( userModel.getEmail() );
        }
        userResponseDto.setRolId( rolResponseDtoToRolResponseDto( rolResponseDto ) );

        return userResponseDto;
    }

    protected RolResponseDto rolResponseDtoToRolResponseDto(RolResponseDto rolResponseDto) {
        if ( rolResponseDto == null ) {
            return null;
        }

        RolResponseDto rolResponseDto1 = new RolResponseDto();

        rolResponseDto1.setName( rolResponseDto.getName() );
        rolResponseDto1.setDescription( rolResponseDto.getDescription() );

        return rolResponseDto1;
    }
}
