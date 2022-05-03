package com.example.lanlineelderdemo.service.restaurant.response;

import com.example.lanlineelderdemo.domain.restaurant.FoodCategory;
import com.example.lanlineelderdemo.domain.restaurant.Location;
import com.example.lanlineelderdemo.repository.restaurant.FindRestaurantBySearchConditionResponseDto;
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

    private Double locationX;

    private Double locationY;

    private String url;

    private Integer minCostPerPerson;


    public static SearchRestaurantResponseDto of(FindRestaurantBySearchConditionResponseDto findRestaurantBySearchConditionResponseDto) {
        SearchRestaurantResponseDto searchRestaurantResponseDto = new SearchRestaurantResponseDto();
        searchRestaurantResponseDto.id = findRestaurantBySearchConditionResponseDto.getId();
        searchRestaurantResponseDto.name = findRestaurantBySearchConditionResponseDto.getName();
        searchRestaurantResponseDto.location = findRestaurantBySearchConditionResponseDto.getLocation();
        searchRestaurantResponseDto.category = findRestaurantBySearchConditionResponseDto.getCategory();
        searchRestaurantResponseDto.isAtmosphere = findRestaurantBySearchConditionResponseDto.getIsAtmosphere();
        searchRestaurantResponseDto.hasCostPerformance = findRestaurantBySearchConditionResponseDto.getHasCostPerformance();
        searchRestaurantResponseDto.canEatSingle = findRestaurantBySearchConditionResponseDto.getCanEatSingle();
        searchRestaurantResponseDto.locationX = findRestaurantBySearchConditionResponseDto.getGeoLocation().getLocationX();
        searchRestaurantResponseDto.locationY = findRestaurantBySearchConditionResponseDto.getGeoLocation().getLocationY();
        searchRestaurantResponseDto.url = findRestaurantBySearchConditionResponseDto.getUrl();
        searchRestaurantResponseDto.minCostPerPerson = findRestaurantBySearchConditionResponseDto.getPrice() / findRestaurantBySearchConditionResponseDto.getNumberOfMeal();

        return searchRestaurantResponseDto;


    }
}
