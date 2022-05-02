package com.example.lanlineelderdemo.service.dto.response;

import com.example.lanlineelderdemo.domain.FoodCategory;
import com.example.lanlineelderdemo.domain.Location;
import com.example.lanlineelderdemo.repository.FindRestaurantBySearchConditionResponseDto;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SearchRestaurantsResponseDto {
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

    private Integer minCostPerPerson;


    public static SearchRestaurantsResponseDto of(FindRestaurantBySearchConditionResponseDto findRestaurantBySearchConditionResponseDto) {
        SearchRestaurantsResponseDto searchRestaurantsResponseDto = new SearchRestaurantsResponseDto();
        searchRestaurantsResponseDto.id = findRestaurantBySearchConditionResponseDto.getId();
        searchRestaurantsResponseDto.name = findRestaurantBySearchConditionResponseDto.getName();
        searchRestaurantsResponseDto.location = findRestaurantBySearchConditionResponseDto.getLocation();
        searchRestaurantsResponseDto.category = findRestaurantBySearchConditionResponseDto.getCategory();
        searchRestaurantsResponseDto.isAtmosphere = findRestaurantBySearchConditionResponseDto.getIsAtmosphere();
        searchRestaurantsResponseDto.hasCostPerformance = findRestaurantBySearchConditionResponseDto.getHasCostPerformance();
        searchRestaurantsResponseDto.canEatSingle = findRestaurantBySearchConditionResponseDto.getCanEatSingle();
        searchRestaurantsResponseDto.adminComment = findRestaurantBySearchConditionResponseDto.getAdminComment();
        searchRestaurantsResponseDto.locationX = findRestaurantBySearchConditionResponseDto.getGeoLocation().getLocationX();
        searchRestaurantsResponseDto.locationY = findRestaurantBySearchConditionResponseDto.getGeoLocation().getLocationY();
        searchRestaurantsResponseDto.url = findRestaurantBySearchConditionResponseDto.getUrl();
        searchRestaurantsResponseDto.minCostPerPerson = findRestaurantBySearchConditionResponseDto.getPrice() / findRestaurantBySearchConditionResponseDto.getNumberOfMeal();

        return searchRestaurantsResponseDto;


    }
}
