package com.example.lanlineelderdemo.repository;

import com.example.lanlineelderdemo.domain.QRestaurant;
import com.example.lanlineelderdemo.domain.Restaurant;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import java.util.List;

import static com.example.lanlineelderdemo.domain.QRestaurant.restaurant;

public class RestaurantRepositoryImpl implements RestaurantRepositoryCustom {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public RestaurantRepositoryImpl(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Restaurant> findRestaurantBySearchDto(SearchRequestRepositoryDto searchRequestRepositoryDto) {

        //TODO 검증 해줘야함. 코드만 지금 작성한 상태.
        // TODO 체크 안한건(false처리) null로 들어가서 무시해줘야함. 분위기? x 했다고 분위기 없는 식당만 추천하는 게 아님.
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
