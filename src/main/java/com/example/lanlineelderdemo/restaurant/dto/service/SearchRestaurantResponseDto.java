package com.example.lanlineelderdemo.restaurant.dto.service;

import com.example.lanlineelderdemo.domain.restaurant.FoodCategory;
import com.example.lanlineelderdemo.domain.restaurant.Location;
import com.example.lanlineelderdemo.restaurant.repository.dto.FindRestaurantBySearchConditionResponseDto;
import com.example.lanlineelderdemo.utils.Encoder;
import lombok.Data;
import lombok.ToString;

@Data
public class SearchRestaurantResponseDto {
    private Long id;

    private String name;

    private Location location;

    private FoodCategory category;

    private Double locationX;

    private Double locationY;

    private Integer minCostPerPerson;


    public static SearchRestaurantResponseDto of(FindRestaurantBySearchConditionResponseDto findRestaurantBySearchConditionResponseDto) {
        SearchRestaurantResponseDto searchRestaurantResponseDto = new SearchRestaurantResponseDto();
        searchRestaurantResponseDto.id = findRestaurantBySearchConditionResponseDto.getId();
        searchRestaurantResponseDto.name = Encoder.encodeUtf8(findRestaurantBySearchConditionResponseDto.getName());
        searchRestaurantResponseDto.location = findRestaurantBySearchConditionResponseDto.getLocation();
        searchRestaurantResponseDto.category = findRestaurantBySearchConditionResponseDto.getCategory();
        searchRestaurantResponseDto.locationX = findRestaurantBySearchConditionResponseDto.getGeoLocation().getLocationX();
        searchRestaurantResponseDto.locationY = findRestaurantBySearchConditionResponseDto.getGeoLocation().getLocationY();
        searchRestaurantResponseDto.minCostPerPerson = findRestaurantBySearchConditionResponseDto.getPrice() / findRestaurantBySearchConditionResponseDto.getNumberOfMeal();

        return searchRestaurantResponseDto;

    }

    @Override
    public String toString() {
        return "SearchRestaurantResponseDto{" +
                "id=" + id +
                "%name=" + name +
                "%location=" + location +
                "%category=" + category +
                "%locationX=" + locationX +
                "%locationY=" + locationY +
                "%minCostPerPerson=" + minCostPerPerson +
                '}';
    }
}