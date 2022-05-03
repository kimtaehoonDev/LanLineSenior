package com.example.lanlineelderdemo.service.menu;

import com.example.lanlineelderdemo.domain.menu.Menu;
import com.example.lanlineelderdemo.domain.menu.OpenType;
import lombok.Data;

import java.util.List;

@Data
public class findRecommendMenuInRestaurantDto {
    private String lunchMenuName;
    private Integer lunchMenuPrice;
    private String dinnerMenuName;
    private Integer dinnerMenuPrice;

    public static findRecommendMenuInRestaurantDto from(List<Menu> restaurantRecommendMenus) {
        findRecommendMenuInRestaurantDto findRecommendMenuInRestaurantDto = new findRecommendMenuInRestaurantDto();
        for (Menu restaurantRecommendMenu : restaurantRecommendMenus) {
            if (restaurantRecommendMenu.getOpenType() == OpenType.BOTH) {
                putLunchMenu(findRecommendMenuInRestaurantDto, restaurantRecommendMenu);
                putDinnerMenu(findRecommendMenuInRestaurantDto, restaurantRecommendMenu);
            } else if (restaurantRecommendMenu.getOpenType() == OpenType.LUNCH) {
                putLunchMenu(findRecommendMenuInRestaurantDto, restaurantRecommendMenu);
            } else if (restaurantRecommendMenu.getOpenType() == OpenType.DINNER) {
                putDinnerMenu(findRecommendMenuInRestaurantDto, restaurantRecommendMenu);
            } else {
                throw new IllegalArgumentException("Menu 관련 정보가 잘못되었습니다.");
            }
        }
        return findRecommendMenuInRestaurantDto;
    }

    private static void putDinnerMenu(findRecommendMenuInRestaurantDto findRecommendMenuInRestaurantDto, Menu restaurantRecommendMenu) {
        findRecommendMenuInRestaurantDto.dinnerMenuName = restaurantRecommendMenu.getMenuName();
        findRecommendMenuInRestaurantDto.dinnerMenuPrice = restaurantRecommendMenu.getPrice();
    }

    private static void putLunchMenu(findRecommendMenuInRestaurantDto findRecommendMenuInRestaurantDto, Menu restaurantRecommendMenu) {
        findRecommendMenuInRestaurantDto.lunchMenuName = restaurantRecommendMenu.getMenuName();
        findRecommendMenuInRestaurantDto.lunchMenuPrice = restaurantRecommendMenu.getPrice();
    }
}
