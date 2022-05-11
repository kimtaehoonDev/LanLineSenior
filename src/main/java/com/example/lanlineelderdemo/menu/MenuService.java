package com.example.lanlineelderdemo.menu;

import com.example.lanlineelderdemo.domain.menu.Menu;
import com.example.lanlineelderdemo.domain.menu.OpenType;
import com.example.lanlineelderdemo.menu.dto.MenuRegisterDto;
import com.example.lanlineelderdemo.menu.dto.RestaurantRecommendMenuDto;
import com.example.lanlineelderdemo.domain.restaurant.Restaurant;
import com.example.lanlineelderdemo.restaurant.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MenuService {
    private final MenuRepository menuRepository;
    private final RestaurantRepository restaurantRepository;

    @Transactional
    public void registerMenus(List<MenuRegisterDto> dataList) {
        for (MenuRegisterDto data : dataList) {
            registerMenu(data.getRestaurantName(), data.getOpenType(), data.getMenuName(),
                    data.getNumberOfMeal(), data.getPrice());
        }
    }

    @Transactional
    public Long registerMenu(String restaurantName, OpenType openType, String menuName, Integer numberOfMeal, Integer price) {
        Menu menu = Menu.createMenu()
                .restaurant(findRestaurant(restaurantName))
                .openType(openType)
                .menuName(menuName)
                .numberOfMeal(numberOfMeal)
                .price(price)
                .build();
        menuRepository.save(menu);
        return menu.getId();
    }

    private Restaurant findRestaurant(String restaurantName) {
        Optional<Restaurant> parsingRestaurant = restaurantRepository.findByName(restaurantName);
        if (parsingRestaurant.isEmpty()) {
            throw new IllegalArgumentException("해당되는 이름을 가진 식당은 존재하지 않습니다.");
        }
        return parsingRestaurant.get();
    }

    public RestaurantRecommendMenuDto findRestaurantRecommendMenu(Long restaurantId) {
        List<Menu> restaurantRecommendMenu = menuRepository.findByRestaurant_Id(restaurantId);
        return RestaurantRecommendMenuDto.from(restaurantRecommendMenu);
    }
}
