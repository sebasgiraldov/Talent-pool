package com.pragma.powerup.infrastructure.out.jpa.mapper;

import com.pragma.powerup.domain.model.RolModel;
import com.pragma.powerup.infrastructure.out.jpa.entity.RolEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-14T14:13:58-0500",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.jar, environment: Java 17.0.7 (Oracle Corporation)"
)
@Component
public class IRolEntityMapperImpl implements IRolEntityMapper {

    @Override
    public RolEntity toEntity(RolModel rolModel) {
        if ( rolModel == null ) {
            return null;
        }

        RolEntity rolEntity = new RolEntity();

        rolEntity.setId( rolModel.getId() );
        rolEntity.setName( rolModel.getName() );
        rolEntity.setDescription( rolModel.getDescription() );

        return rolEntity;
    }

    @Override
    public RolModel toRolModel(RolEntity rolEntity) {
        if ( rolEntity == null ) {
            return null;
        }

        RolModel rolModel = new RolModel();

        rolModel.setId( rolEntity.getId() );
        rolModel.setName( rolEntity.getName() );
        rolModel.setDescription( rolEntity.getDescription() );

        return rolModel;
    }
}
