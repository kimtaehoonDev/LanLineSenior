package com.example.lanlineelderdemo.repository;

import com.example.lanlineelderdemo.domain.QRestaurant;
import com.example.lanlineelderdemo.domain.Restaurant;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.example.lanlineelderdemo.domain.QRestaurant.restaurant;

@RequiredArgsConstructor
public class RestaurantRepositoryImpl implements RestaurantRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Restaurant> findRestaurantBySearchDto(SearchRequestRepositoryDto searchRequestRepositoryDto) {

        //TODO 검증 해줘야함. 코드만 지금 작성한 상태.
        return queryFactory.select(restaurant)
                .from(restaurant)
                .where(restaurant.location.in(searchRequestRepositoryDto.getLocations()),
                        restaurant.category.notIn(searchRequestRepositoryDto.getUnselectedCategories()),
                        restaurant.isAtmosphere.eq(searchRequestRepositoryDto.getIsAtmosphere()),
                        restaurant.canEatSingle.eq(searchRequestRepositoryDto.getCanEatSingle()),
                        restaurant.hasCostPerformance.eq(searchRequestRepositoryDto.getHasCostPerformance()),
                        restaurant.minCost.loe(searchRequestRepositoryDto.getMaxCostLine())
                )
                .orderBy(NumberExpression.random().asc())
                .limit(5)
                .fetch();
    }
}
