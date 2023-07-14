package com.pragma.powerup.application.mapper;

import com.pragma.powerup.application.dto.response.RolResponseDto;
import com.pragma.powerup.domain.model.RolModel;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-14T14:13:58-0500",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.jar, environment: Java 17.0.7 (Oracle Corporation)"
)
@Component
public class IRolResponseMapperImpl implements IRolResponseMapper {

    @Override
    public RolResponseDto toResponse(RolModel rolModel) {
        if ( rolModel == null ) {
            return null;
        }

        RolResponseDto rolResponseDto = new RolResponseDto();

        rolResponseDto.setName( rolModel.getName() );
        rolResponseDto.setDescription( rolModel.getDescription() );

        return rolResponseDto;
    }
}
