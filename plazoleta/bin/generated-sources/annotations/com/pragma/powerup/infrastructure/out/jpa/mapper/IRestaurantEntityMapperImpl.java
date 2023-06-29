package com.pragma.powerup.infrastructure.out.jpa.mapper;

import com.pragma.powerup.domain.model.RestaurantModel;
import com.pragma.powerup.infrastructure.out.jpa.entity.RestaurantEntity;
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
public class IRestaurantEntityMapperImpl implements IRestaurantEntityMapper {

    @Override
    public RestaurantEntity toEntity(RestaurantModel restaurantModel) {
        if ( restaurantModel == null ) {
            return null;
        }

        RestaurantEntity restaurantEntity = new RestaurantEntity();

        restaurantEntity.setAddress( restaurantModel.getAddress() );
        restaurantEntity.setId( restaurantModel.getId() );
        restaurantEntity.setIdOwner( restaurantModel.getIdOwner() );
        restaurantEntity.setName( restaurantModel.getName() );
        restaurantEntity.setNit( restaurantModel.getNit() );
        restaurantEntity.setPhone( restaurantModel.getPhone() );
        restaurantEntity.setUrlLogo( restaurantModel.getUrlLogo() );

        return restaurantEntity;
    }

    @Override
    public RestaurantModel toRestaurantModel(RestaurantEntity restaurantEntity) {
        if ( restaurantEntity == null ) {
            return null;
        }

        RestaurantModel restaurantModel = new RestaurantModel();

        restaurantModel.setAddress( restaurantEntity.getAddress() );
        restaurantModel.setId( restaurantEntity.getId() );
        restaurantModel.setIdOwner( restaurantEntity.getIdOwner() );
        restaurantModel.setName( restaurantEntity.getName() );
        restaurantModel.setNit( restaurantEntity.getNit() );
        restaurantModel.setPhone( restaurantEntity.getPhone() );
        restaurantModel.setUrlLogo( restaurantEntity.getUrlLogo() );

        return restaurantModel;
    }

    @Override
    public List<RestaurantModel> toRestaurantModelList(List<RestaurantEntity> restaurantEntityList) {
        if ( restaurantEntityList == null ) {
            return null;
        }

        List<RestaurantModel> list = new ArrayList<RestaurantModel>( restaurantEntityList.size() );
        for ( RestaurantEntity restaurantEntity : restaurantEntityList ) {
            list.add( toRestaurantModel( restaurantEntity ) );
        }

        return list;
    }
}
