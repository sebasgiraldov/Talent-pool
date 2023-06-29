package com.pragma.powerup.application.mapper;

import com.pragma.powerup.application.dto.request.UserRequestDto;
import com.pragma.powerup.application.dto.response.UserResponseDto;
import com.pragma.powerup.domain.model.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-29T09:41:17-0500",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.jar, environment: Java 11.0.18 (Oracle Corporation)"
)
@Component
public class IUserResponseMapperImpl implements IUserResponseMapper {

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

    @Override
    public List<UserResponseDto> toResponseList(List<User> userList) {
        if ( userList == null ) {
            return null;
        }

        List<UserResponseDto> list = new ArrayList<UserResponseDto>( userList.size() );
        for ( User user : userList ) {
            list.add( toResponse( user ) );
        }

        return list;
    }

    @Override
    public UserResponseDto toResponse(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponseDto userResponseDto = new UserResponseDto();

        userResponseDto.setId( user.getId() );
        userResponseDto.setName( user.getName() );
        userResponseDto.setLastname( user.getLastname() );
        userResponseDto.setDocument( user.getDocument() );
        userResponseDto.setPhone( user.getPhone() );
        userResponseDto.setBirthDate( user.getBirthDate() );
        userResponseDto.setEmail( user.getEmail() );

        return userResponseDto;
    }
}
