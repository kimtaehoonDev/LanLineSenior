package com.example.lanlineelderdemo.service;

import com.example.lanlineelderdemo.domain.Menu;
import com.example.lanlineelderdemo.domain.OpenType;
import com.example.lanlineelderdemo.domain.Restaurant;
import com.example.lanlineelderdemo.repository.MenuRepository;
import com.example.lanlineelderdemo.repository.RestaurantRepository;
import com.example.lanlineelderdemo.service.dto.request.RegisterMenuRequestServiceDto;
import com.example.lanlineelderdemo.service.dto.request.RegisterRequestServiceDto;
import com.example.lanlineelderdemo.service.dto.response.FindRestaurantRecommendMenuResponseDto;
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
    public void registerMenus(List<RegisterMenuRequestServiceDto> dataList) {
        for (RegisterMenuRequestServiceDto data : dataList) {
            registerMenu(data.getRestaurantName(), data.getOpenType(), data.getMenuName(), data.getNumberOfMeal(), data.getPrice());
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

    public FindRestaurantRecommendMenuResponseDto findRestaurantRecommendMenu(Long restaurantId) {
        List<Menu> restaurantRecommendMenus = menuRepository.findByRestaurant_Id(restaurantId);
        return FindRestaurantRecommendMenuResponseDto.of(restaurantRecommendMenus);
    }
}
