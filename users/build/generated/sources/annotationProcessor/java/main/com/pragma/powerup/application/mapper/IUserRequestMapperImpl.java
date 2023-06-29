package com.pragma.powerup.application.mapper;

import com.pragma.powerup.application.dto.request.UserRequestDto;
import com.pragma.powerup.domain.model.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-29T11:38:20-0500",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.jar, environment: Java 17.0.7 (Oracle Corporation)"
)
@Component
public class IUserRequestMapperImpl implements IUserRequestMapper {

    @Override
    public User toUser(UserRequestDto userRequestDto) {
        if ( userRequestDto == null ) {
            return null;
        }

        User user = new User();

        user.setName( userRequestDto.getName() );
        user.setLastname( userRequestDto.getLastname() );
        user.setDocument( userRequestDto.getDocument() );
        user.setPhone( userRequestDto.getPhone() );
        user.setBirthDate( userRequestDto.getBirthDate() );
        user.setEmail( userRequestDto.getEmail() );
        user.setPass( userRequestDto.getPass() );

        return user;
    }
}
