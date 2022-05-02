package com.example.lanlineelderdemo.service.dto.response;

import com.example.lanlineelderdemo.domain.Menu;
import com.example.lanlineelderdemo.domain.OpenType;
import lombok.Data;

import java.util.List;

@Data
public class FindRestaurantRecommendMenuResponseDto {
    private String lunchMenuName;
    private Integer lunchMenuPrice;
    private String dinnerMenuName;
    private Integer dinnerMenuPrice;

    public static FindRestaurantRecommendMenuResponseDto of(List<Menu> restaurantRecommendMenus) {
        FindRestaurantRecommendMenuResponseDto findRestaurantRecommendMenuResponseDto = new FindRestaurantRecommendMenuResponseDto();
        for (Menu restaurantRecommendMenu : restaurantRecommendMenus) {
            if (restaurantRecommendMenu.getOpenType() == OpenType.BOTH) {
                findRestaurantRecommendMenuResponseDto.lunchMenuName = restaurantRecommendMenu.getMenuName();
                findRestaurantRecommendMenuResponseDto.lunchMenuPrice = restaurantRecommendMenu.getPrice();
                findRestaurantRecommendMenuResponseDto.dinnerMenuName = restaurantRecommendMenu.getMenuName();
                findRestaurantRecommendMenuResponseDto.dinnerMenuPrice = restaurantRecommendMenu.getPrice();
                break;
            } else if (restaurantRecommendMenu.getOpenType() == OpenType.LUNCH) {
                findRestaurantRecommendMenuResponseDto.lunchMenuName = restaurantRecommendMenu.getMenuName();
                findRestaurantRecommendMenuResponseDto.lunchMenuPrice = restaurantRecommendMenu.getPrice();
            } else if (restaurantRecommendMenu.getOpenType() == OpenType.DINNER) {
                findRestaurantRecommendMenuResponseDto.dinnerMenuName = restaurantRecommendMenu.getMenuName();
                findRestaurantRecommendMenuResponseDto.dinnerMenuPrice = restaurantRecommendMenu.getPrice();
            } else {
                throw new IllegalArgumentException("Menu 관련 정보가 잘못되었습니다.");
            }
        }
        return findRestaurantRecommendMenuResponseDto;
    }
}
