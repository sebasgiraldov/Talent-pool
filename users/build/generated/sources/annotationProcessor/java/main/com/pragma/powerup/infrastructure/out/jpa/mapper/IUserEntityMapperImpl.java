package com.pragma.powerup.infrastructure.out.jpa.mapper;

import com.pragma.powerup.domain.model.User;
import com.pragma.powerup.infrastructure.out.jpa.entity.UserEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-29T11:38:20-0500",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.jar, environment: Java 17.0.7 (Oracle Corporation)"
)
@Component
public class IUserEntityMapperImpl implements IUserEntityMapper {

    @Override
    public UserEntity toEntity(User user) {
        if ( user == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setId( user.getId() );
        userEntity.setName( user.getName() );
        userEntity.setLastname( user.getLastname() );
        userEntity.setDocument( user.getDocument() );
        userEntity.setPhone( user.getPhone() );
        userEntity.setBirthDate( user.getBirthDate() );
        userEntity.setEmail( user.getEmail() );
        userEntity.setPass( user.getPass() );
        userEntity.setRole( user.getRole() );

        return userEntity;
    }

    @Override
    public User toUser(UserEntity userEntity) {
        if ( userEntity == null ) {
            return null;
        }

        User user = new User();

        user.setId( userEntity.getId() );
        user.setName( userEntity.getName() );
        user.setLastname( userEntity.getLastname() );
        user.setDocument( userEntity.getDocument() );
        user.setPhone( userEntity.getPhone() );
        user.setBirthDate( userEntity.getBirthDate() );
        user.setEmail( userEntity.getEmail() );
        user.setPass( userEntity.getPass() );
        user.setRole( userEntity.getRole() );

        return user;
    }

    @Override
    public List<User> toUserList(List<UserEntity> userEntityList) {
        if ( userEntityList == null ) {
            return null;
        }

        List<User> list = new ArrayList<User>( userEntityList.size() );
        for ( UserEntity userEntity : userEntityList ) {
            list.add( toUser( userEntity ) );
        }

        return list;
    }
}
