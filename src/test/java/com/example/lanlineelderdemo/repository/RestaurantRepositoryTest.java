package com.example.lanlineelderdemo.repository;

import com.example.lanlineelderdemo.domain.FoodCategory;
import com.example.lanlineelderdemo.domain.Location;
import com.example.lanlineelderdemo.domain.Restaurant;
import com.example.lanlineelderdemo.domain.SearchCondition;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
@Transactional
class RestaurantRepositoryTest {
    @Autowired RestaurantRepository restaurantRepository;

    @Test
    void 식당_저장() {
        Restaurant restaurant = Restaurant.createRestaurant()
                .name("교촌치킨 전대점")
                .location(Location.BACK_DOOR)
                .category(FoodCategory.CHICKEN)
                .adminComment("맛있어요~ 분위기 거의 푸라닭")
                .canEatSingle(false)
                .hasCostPerformance(false)
                .isAtmosphere(true)
                .maxCost(30000)
                .minCost(15000)
                .build();

        restaurantRepository.save(restaurant);

        Restaurant findRestaurant = restaurantRepository.findAll().get(0);
        Assertions.assertThat(findRestaurant.getName()).isEqualTo(restaurant.getName());
    }

    @Test
    void 식당_검색() {
        Restaurant restaurant = Restaurant.createRestaurant()
                .name("교촌치킨 전대점")
                .location(Location.BACK_DOOR)
                .category(FoodCategory.CHICKEN)
                .adminComment("맛있어요~ 분위기 거의 푸라닭")
                .canEatSingle(false)
                .hasCostPerformance(false)
                .isAtmosphere(true)
                .maxCost(30000)
                .minCost(15000)
                .build();

        Restaurant restaurant2 = Restaurant.createRestaurant()
                .name("쭈군")
                .location(Location.BACK_DOOR)
                .category(FoodCategory.KOREAN)
                .adminComment("사이드메뉴도 좋고 맛있어용!")
                .canEatSingle(false)
                .hasCostPerformance(false)
                .isAtmosphere(true)
                .maxCost(30000)
                .minCost(20000)
                .build();
        restaurantRepository.save(restaurant);
        restaurantRepository.save(restaurant2);

        SearchCondition search = new SearchCondition();
        search.setCanEatSingle(false);
        search.setIsAtmosphere(true); //체크 안한건 false 아니라 null?
        search.setLocations(new ArrayList<>(Arrays.asList(Location.BACK_DOOR, Location.BOKGAE)));
        search.setHasCostPerformance(false);
        search.setMaxCostLine(16000);
        search.setUnselectedCategories(new ArrayList<>());
        List<Restaurant> restaurantList = restaurantRepository.findRestaurantBySearchCondition(search);

        Assertions.assertThat(restaurantList.size()).isEqualTo(1);
        Assertions.assertThat(restaurantList.get(0).getName()).isEqualTo(restaurant.getName());
    }

}