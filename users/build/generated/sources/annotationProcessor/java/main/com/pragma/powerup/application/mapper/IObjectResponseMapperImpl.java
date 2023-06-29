package com.pragma.powerup.application.mapper;

import com.pragma.powerup.application.dto.response.ObjectResponseDto;
import com.pragma.powerup.domain.model.ObjectModel;
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
public class IObjectResponseMapperImpl implements IObjectResponseMapper {

    @Override
    public ObjectResponseDto toResponse(ObjectModel objectModel) {
        if ( objectModel == null ) {
            return null;
        }

        ObjectResponseDto objectResponseDto = new ObjectResponseDto();

        objectResponseDto.setId( objectModel.getId() );
        objectResponseDto.setName( objectModel.getName() );

        return objectResponseDto;
    }

    @Override
    public List<ObjectResponseDto> toResponseList(List<ObjectModel> objectModelList) {
        if ( objectModelList == null ) {
            return null;
        }

        List<ObjectResponseDto> list = new ArrayList<ObjectResponseDto>( objectModelList.size() );
        for ( ObjectModel objectModel : objectModelList ) {
            list.add( toResponse( objectModel ) );
        }

        return list;
    }
}
