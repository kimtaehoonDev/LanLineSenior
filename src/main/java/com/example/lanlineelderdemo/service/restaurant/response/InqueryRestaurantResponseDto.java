package com.example.lanlineelderdemo.service.restaurant.response;

import com.example.lanlineelderdemo.domain.restaurant.FoodCategory;
import com.example.lanlineelderdemo.domain.restaurant.Location;
import com.example.lanlineelderdemo.domain.restaurant.Restaurant;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class InqueryRestaurantResponseDto {
    private Long id;

    private String name;

    private Location location;

    private FoodCategory category;

    private Boolean isAtmosphere;

    private Boolean hasCostPerformance;

    private Boolean canEatSingle;

    private String adminComment;

    private Double locationX;

    private Double locationY;

    private String url;

    public static InqueryRestaurantResponseDto of(Restaurant restaurant) {
        InqueryRestaurantResponseDto inqueryRestaurantResponseDto = new InqueryRestaurantResponseDto();
        inqueryRestaurantResponseDto.id = restaurant.getId();
        inqueryRestaurantResponseDto.name = restaurant.getName();
        inqueryRestaurantResponseDto.location = restaurant.getLocation();
        inqueryRestaurantResponseDto.category = restaurant.getCategory();
        inqueryRestaurantResponseDto.isAtmosphere = restaurant.getIsAtmosphere();
        inqueryRestaurantResponseDto.hasCostPerformance = restaurant.getHasCostPerformance();
        inqueryRestaurantResponseDto.canEatSingle = restaurant.getCanEatSingle();
        inqueryRestaurantResponseDto.adminComment = restaurant.getAdminComment();
        inqueryRestaurantResponseDto.locationX = restaurant.getGeoLocation().getLocationX();
        inqueryRestaurantResponseDto.locationY = restaurant.getGeoLocation().getLocationY();
        inqueryRestaurantResponseDto.url = restaurant.getUrl();

        return inqueryRestaurantResponseDto;
    }
}
