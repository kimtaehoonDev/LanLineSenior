package com.example.lanlineelderdemo.service.dto.response;

import com.example.lanlineelderdemo.domain.FoodCategory;
import com.example.lanlineelderdemo.domain.Location;
import com.example.lanlineelderdemo.domain.Restaurant;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SearchRestaurantResponseDto {
    private Long id;

    private String name;

    private Location location;

    private FoodCategory category;

    private Boolean isAtmosphere;

    private Boolean hasCostPerformance;

    private Boolean canEatSingle;

    private String adminComment;

    private Integer minCost;

    private Double locationX;

    private Double locationY;

    private String address;

    private String url;

    public static SearchRestaurantResponseDto of(Restaurant restaurant) {
        SearchRestaurantResponseDto searchRestaurantResponseDto = new SearchRestaurantResponseDto();
        searchRestaurantResponseDto.id = restaurant.getId();
        searchRestaurantResponseDto.name = restaurant.getName();
        searchRestaurantResponseDto.location = restaurant.getLocation();
        searchRestaurantResponseDto.category = restaurant.getCategory();
        searchRestaurantResponseDto.isAtmosphere = restaurant.getIsAtmosphere();
        searchRestaurantResponseDto.hasCostPerformance = restaurant.getHasCostPerformance();
        searchRestaurantResponseDto.canEatSingle = restaurant.getCanEatSingle();
        searchRestaurantResponseDto.adminComment = restaurant.getAdminComment();
        searchRestaurantResponseDto.minCost = restaurant.getMinCost();
        searchRestaurantResponseDto.locationX = restaurant.getGeoLocation().getLocationX();
        searchRestaurantResponseDto.locationY = restaurant.getGeoLocation().getLocationY();
        searchRestaurantResponseDto.address = restaurant.getAddress();
        searchRestaurantResponseDto.url = restaurant.getUrl();

        return searchRestaurantResponseDto;


    }
}
