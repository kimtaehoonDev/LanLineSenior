package com.example.lanlineelderdemo.menu.dto;

import com.example.lanlineelderdemo.domain.menu.Menu;
import com.example.lanlineelderdemo.domain.menu.OpenType;
import lombok.Data;

import java.util.List;

@Data
public class RestaurantRecommendMenuDto {
    private String lunchMenuName;
    private Integer lunchMenuPrice;
    private String dinnerMenuName;
    private Integer dinnerMenuPrice;

    public static RestaurantRecommendMenuDto from(List<Menu> restaurantRecommendMenus) {
        RestaurantRecommendMenuDto RestaurantRecommendMenuDto = new RestaurantRecommendMenuDto();
        for (Menu restaurantRecommendMenu : restaurantRecommendMenus) {
            if (restaurantRecommendMenu.getOpenType() == OpenType.BOTH) {
                putLunchMenu(RestaurantRecommendMenuDto, restaurantRecommendMenu);
                putDinnerMenu(RestaurantRecommendMenuDto, restaurantRecommendMenu);
            } else if (restaurantRecommendMenu.getOpenType() == OpenType.LUNCH) {
                putLunchMenu(RestaurantRecommendMenuDto, restaurantRecommendMenu);
            } else if (restaurantRecommendMenu.getOpenType() == OpenType.DINNER) {
                putDinnerMenu(RestaurantRecommendMenuDto, restaurantRecommendMenu);
            } else {
                throw new IllegalArgumentException("Menu 관련 정보가 잘못되었습니다.");
            }
        }
        return RestaurantRecommendMenuDto;
    }

    private static void putDinnerMenu(RestaurantRecommendMenuDto RestaurantRecommendMenuDto, Menu restaurantRecommendMenu) {
        RestaurantRecommendMenuDto.dinnerMenuName = restaurantRecommendMenu.getMenuName();
        RestaurantRecommendMenuDto.dinnerMenuPrice = restaurantRecommendMenu.getPrice();
    }

    private static void putLunchMenu(RestaurantRecommendMenuDto RestaurantRecommendMenuDto, Menu restaurantRecommendMenu) {
        RestaurantRecommendMenuDto.lunchMenuName = restaurantRecommendMenu.getMenuName();
        RestaurantRecommendMenuDto.lunchMenuPrice = restaurantRecommendMenu.getPrice();
    }
}
