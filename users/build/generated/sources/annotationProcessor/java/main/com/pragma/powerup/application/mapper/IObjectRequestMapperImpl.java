package com.pragma.powerup.application.mapper;

import com.pragma.powerup.application.dto.request.ObjectRequestDto;
import com.pragma.powerup.domain.model.ObjectModel;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-29T09:41:17-0500",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.jar, environment: Java 11.0.18 (Oracle Corporation)"
)
@Component
public class IObjectRequestMapperImpl implements IObjectRequestMapper {

    @Override
    public ObjectModel toObject(ObjectRequestDto objectRequestDto) {
        if ( objectRequestDto == null ) {
            return null;
        }

        ObjectModel objectModel = new ObjectModel();

        objectModel.setName( objectRequestDto.getName() );

        return objectModel;
    }
}
