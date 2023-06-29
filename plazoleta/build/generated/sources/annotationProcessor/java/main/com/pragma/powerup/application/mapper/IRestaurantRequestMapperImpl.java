package com.pragma.powerup.application.mapper;

import com.pragma.powerup.application.dto.request.RestaurantRequestDto;
import com.pragma.powerup.domain.model.RestaurantModel;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-28T16:37:36-0500",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.jar, environment: Java 11.0.18 (Oracle Corporation)"
)
@Component
public class IRestaurantRequestMapperImpl implements IRestaurantRequestMapper {

    @Override
    public RestaurantModel toRestaurant(RestaurantRequestDto restaurantRequestDto) {
        if ( restaurantRequestDto == null ) {
            return null;
        }

        RestaurantModel restaurantModel = new RestaurantModel();

        restaurantModel.setName( restaurantRequestDto.getName() );
        restaurantModel.setNit( restaurantRequestDto.getNit() );
        restaurantModel.setAddress( restaurantRequestDto.getAddress() );
        restaurantModel.setPhone( restaurantRequestDto.getPhone() );
        restaurantModel.setUrlLogo( restaurantRequestDto.getUrlLogo() );
        restaurantModel.setIdOwner( restaurantRequestDto.getIdOwner() );

        return restaurantModel;
    }
}
