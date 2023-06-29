package com.pragma.powerup.application.mapper;

import com.pragma.powerup.application.dto.request.RestaurantRequestDto;
import com.pragma.powerup.application.dto.response.RestaurantResponseDto;
import com.pragma.powerup.domain.model.RestaurantModel;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-29T11:09:47-0500",
    comments = "version: 1.5.2.Final, compiler: Eclipse JDT (IDE) 3.35.0.v20230622-1425, environment: Java 17.0.7 (Eclipse Adoptium)"
)
@Component
public class IRestaurantResponseMapperImpl implements IRestaurantResponseMapper {

    @Override
    public RestaurantModel toRestaurant(RestaurantRequestDto restaurantRequestDto) {
        if ( restaurantRequestDto == null ) {
            return null;
        }

        RestaurantModel restaurantModel = new RestaurantModel();

        restaurantModel.setAddress( restaurantRequestDto.getAddress() );
        restaurantModel.setIdOwner( restaurantRequestDto.getIdOwner() );
        restaurantModel.setName( restaurantRequestDto.getName() );
        restaurantModel.setNit( restaurantRequestDto.getNit() );
        restaurantModel.setPhone( restaurantRequestDto.getPhone() );
        restaurantModel.setUrlLogo( restaurantRequestDto.getUrlLogo() );

        return restaurantModel;
    }

    @Override
    public RestaurantResponseDto toResponse(RestaurantModel restaurantModel) {
        if ( restaurantModel == null ) {
            return null;
        }

        RestaurantResponseDto restaurantResponseDto = new RestaurantResponseDto();

        restaurantResponseDto.setAddress( restaurantModel.getAddress() );
        restaurantResponseDto.setIdOwner( restaurantModel.getIdOwner() );
        restaurantResponseDto.setName( restaurantModel.getName() );
        restaurantResponseDto.setNit( restaurantModel.getNit() );
        restaurantResponseDto.setPhone( restaurantModel.getPhone() );
        restaurantResponseDto.setUrlLogo( restaurantModel.getUrlLogo() );

        return restaurantResponseDto;
    }

    @Override
    public List<RestaurantResponseDto> toResponseList(List<RestaurantModel> restaurantModelList) {
        if ( restaurantModelList == null ) {
            return null;
        }

        List<RestaurantResponseDto> list = new ArrayList<RestaurantResponseDto>( restaurantModelList.size() );
        for ( RestaurantModel restaurantModel : restaurantModelList ) {
            list.add( toResponse( restaurantModel ) );
        }

        return list;
    }
}
