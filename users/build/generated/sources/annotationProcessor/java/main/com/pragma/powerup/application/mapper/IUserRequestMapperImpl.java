package com.pragma.powerup.application.mapper;

import com.pragma.powerup.application.dto.request.RegisterRequestDto;
import com.pragma.powerup.application.dto.request.UserRequestDto;
import com.pragma.powerup.application.dto.response.RolResponseDto;
import com.pragma.powerup.application.dto.response.UserResponseDto;
import com.pragma.powerup.domain.model.RolModel;
import com.pragma.powerup.domain.model.UserModel;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-05T14:38:48-0500",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.jar, environment: Java 17.0.7 (Oracle Corporation)"
)
@Component
public class IUserRequestMapperImpl implements IUserRequestMapper {

    @Override
    public UserModel toUser(UserRequestDto userRequestDto) {
        if ( userRequestDto == null ) {
            return null;
        }

        UserModel userModel = new UserModel();

        userModel.setRolId( userRequestDtoToRolModel( userRequestDto ) );
        userModel.setName( userRequestDto.getName() );
        userModel.setLastName( userRequestDto.getLastName() );
        userModel.setIdNumber( userRequestDto.getIdNumber() );
        userModel.setPhone( userRequestDto.getPhone() );
        userModel.setEmail( userRequestDto.getEmail() );
        userModel.setPassword( userRequestDto.getPassword() );

        return userModel;
    }

    @Override
    public UserResponseDto toDto(UserModel user) {
        if ( user == null ) {
            return null;
        }

        UserResponseDto userResponseDto = new UserResponseDto();

        userResponseDto.setId( user.getId() );
        userResponseDto.setName( user.getName() );
        userResponseDto.setLastName( user.getLastName() );
        userResponseDto.setIdNumber( user.getIdNumber() );
        userResponseDto.setPhone( user.getPhone() );
        userResponseDto.setEmail( user.getEmail() );
        userResponseDto.setRolId( rolModelToRolResponseDto( user.getRolId() ) );

        return userResponseDto;
    }

    @Override
    public UserRequestDto toUserRequestDto(RegisterRequestDto registerRequestDto) {
        if ( registerRequestDto == null ) {
            return null;
        }

        UserRequestDto userRequestDto = new UserRequestDto();

        userRequestDto.setName( registerRequestDto.getName() );
        userRequestDto.setLastName( registerRequestDto.getLastName() );
        userRequestDto.setIdNumber( registerRequestDto.getIdNumber() );
        userRequestDto.setPhone( registerRequestDto.getPhone() );
        userRequestDto.setEmail( registerRequestDto.getEmail() );
        userRequestDto.setPassword( registerRequestDto.getPassword() );

        return userRequestDto;
    }

    protected RolModel userRequestDtoToRolModel(UserRequestDto userRequestDto) {
        if ( userRequestDto == null ) {
            return null;
        }

        RolModel rolModel = new RolModel();

        rolModel.setId( userRequestDto.getRolId() );

        return rolModel;
    }

    protected RolResponseDto rolModelToRolResponseDto(RolModel rolModel) {
        if ( rolModel == null ) {
            return null;
        }

        RolResponseDto rolResponseDto = new RolResponseDto();

        rolResponseDto.setName( rolModel.getName() );
        rolResponseDto.setDescription( rolModel.getDescription() );

        return rolResponseDto;
    }
}
