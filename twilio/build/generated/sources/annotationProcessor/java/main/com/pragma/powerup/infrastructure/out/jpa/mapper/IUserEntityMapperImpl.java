package com.pragma.powerup.infrastructure.out.jpa.mapper;

import com.pragma.powerup.domain.model.RolModel;
import com.pragma.powerup.domain.model.UserModel;
import com.pragma.powerup.infrastructure.out.jpa.entity.RolEntity;
import com.pragma.powerup.infrastructure.out.jpa.entity.UserEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-11T14:26:42-0500",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.jar, environment: Java 17.0.7 (Oracle Corporation)"
)
@Component
public class IUserEntityMapperImpl implements IUserEntityMapper {

    @Override
    public UserEntity toEntity(UserModel userModel) {
        if ( userModel == null ) {
            return null;
        }

        UserEntity.UserEntityBuilder userEntity = UserEntity.builder();

        userEntity.id( userModel.getId() );
        userEntity.name( userModel.getName() );
        userEntity.lastName( userModel.getLastName() );
        userEntity.idNumber( userModel.getIdNumber() );
        userEntity.phone( userModel.getPhone() );
        userEntity.email( userModel.getEmail() );
        userEntity.password( userModel.getPassword() );
        userEntity.rolId( rolModelToRolEntity( userModel.getRolId() ) );

        return userEntity.build();
    }

    protected RolEntity rolModelToRolEntity(RolModel rolModel) {
        if ( rolModel == null ) {
            return null;
        }

        RolEntity rolEntity = new RolEntity();

        rolEntity.setId( rolModel.getId() );
        rolEntity.setName( rolModel.getName() );
        rolEntity.setDescription( rolModel.getDescription() );

        return rolEntity;
    }
}
