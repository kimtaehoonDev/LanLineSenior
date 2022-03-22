package com.example.lanlineelderdemo.repository;

import com.example.lanlineelderdemo.domain.FoodCategory;
import com.example.lanlineelderdemo.domain.Location;
import com.example.lanlineelderdemo.domain.Restaurant;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

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

}