package com.example.lanlineelderdemo.repository;

import com.example.lanlineelderdemo.SearchCondition;
import com.example.lanlineelderdemo.domain.menu.OpenType;
import com.example.lanlineelderdemo.restaurant.domain.FoodCategory;
import com.example.lanlineelderdemo.restaurant.domain.Location;
import com.example.lanlineelderdemo.restaurant.domain.Restaurant;
import com.example.lanlineelderdemo.restaurant.repository.dto.FindRestaurantBySearchConditionResponseDto;
import com.example.lanlineelderdemo.restaurant.repository.RestaurantRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
@Transactional
class RestaurantRepositoryTest {
    @Autowired
    RestaurantRepository restaurantRepository;

    @Test
    void 식당_저장() {
        Restaurant restaurant = Restaurant.createRestaurant()
                .name("교촌치킨 전대점")
                .location(Location.BACK_DOOR)
                .geoLocationX(35.175489)
                .geoLocationY(126.914256)
                .category(FoodCategory.CHICKEN)
                .adminComment("맛있어요~ 분위기 거의 푸라닭")
                .canEatSingle(false)
                .hasCostPerformance(false)
                .isAtmosphere(true)
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
                .geoLocationX(35.175489)
                .geoLocationY(126.914256)
                .category(FoodCategory.CHICKEN)
                .adminComment("맛있어요~ 분위기 거의 푸라닭")
                .canEatSingle(false)
                .hasCostPerformance(false)
                .isAtmosphere(true)
                .build();

        Restaurant restaurant2 = Restaurant.createRestaurant()
                .name("쭈군")
                .location(Location.BACK_DOOR)
                .geoLocationX(34.175489)
                .geoLocationY(125.914256) //아무 좌표나 찍음
                .category(FoodCategory.KOREAN)
                .adminComment("사이드메뉴도 좋고 맛있어용!")
                .canEatSingle(false)
                .hasCostPerformance(false)
                .isAtmosphere(true)
                .build();
        restaurantRepository.save(restaurant);
        restaurantRepository.save(restaurant2);

        SearchCondition search = new SearchCondition(Arrays.asList(Location.BACK_DOOR, Location.BOKGAE), Arrays.asList(), true, null, null, 16000, OpenType.LUNCH);

        List<FindRestaurantBySearchConditionResponseDto> restaurantList = restaurantRepository.findRestaurantBySearchCondition(search);

        Assertions.assertThat(restaurantList.size()).isEqualTo(1);
        Assertions.assertThat(restaurantList.get(0).getName()).isEqualTo(restaurant.getName());
    }

}