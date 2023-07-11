package com.pragma.powerup.application.mapper;

import com.pragma.powerup.application.dto.request.UserRequestDto;
import com.pragma.powerup.domain.model.UserModel;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-11T14:26:42-0500",
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

        userModel.setId( userRequestDto.getId() );
        userModel.setName( userRequestDto.getName() );
        userModel.setEmail( userRequestDto.getEmail() );
        userModel.setPassword( userRequestDto.getPassword() );

        return userModel;
    }
}
