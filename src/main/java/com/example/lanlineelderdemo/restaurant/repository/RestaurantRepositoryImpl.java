package com.example.lanlineelderdemo.restaurant.repository;

import com.example.lanlineelderdemo.domain.SearchCondition;
import com.example.lanlineelderdemo.domain.menu.OpenType;
import com.example.lanlineelderdemo.domain.restaurant.Location;
import com.example.lanlineelderdemo.restaurant.repository.dto.FindRestaurantBySearchConditionResponseDto;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

import static com.example.lanlineelderdemo.domain.menu.QMenu.menu;
import static com.example.lanlineelderdemo.domain.restaurant.QRestaurant.restaurant;

public class RestaurantRepositoryImpl implements RestaurantRepositoryCustom {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public RestaurantRepositoryImpl(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<FindRestaurantBySearchConditionResponseDto> findRestaurantBySearchCondition(SearchCondition searchCondition) {

        return queryFactory.select(Projections.bean(FindRestaurantBySearchConditionResponseDto.class,
                        menu.openType, menu.menuName, menu.numberOfMeal, menu.price,
                        menu.restaurant.id, menu.restaurant.name, menu.restaurant.location,
                        menu.restaurant.geoLocation, menu.restaurant.category, menu.restaurant.isAtmosphere,
                        menu.restaurant.hasCostPerformance, menu.restaurant.canEatSingle,
                        menu.restaurant.adminComment, menu.restaurant.url))
                .from(menu)
                .join(menu.restaurant, restaurant)
                .where(includeLocations(searchCondition.getLocations()),
                        restaurant.category.notIn(searchCondition.getUnselectedCategories()),
                        eqIsAtmosphere(searchCondition.getIsAtmosphere()),
                        eqCanEatSingle(searchCondition.getCanEatSingle()),
                        eqHasCostPerformance(searchCondition.getHasCostPerformance()),
                        menu.openType.in(searchCondition.getOpenType(), OpenType.BOTH),
                        menu.price.divide(menu.numberOfMeal).loe(searchCondition.getMaxCostLine()))
                .orderBy(NumberExpression.random().asc())
                .limit(5)
                .fetch(); //TODO애가 문제?
    }

    private BooleanExpression includeLocations(List<Location> locations) {
        if (locations.isEmpty()) {
            return null;
        }
        return restaurant.location.in(locations);
    }


    private BooleanExpression eqHasCostPerformance(Boolean costPerformance) {
        if (costPerformance == null) {
            return null;
        }
        return restaurant.hasCostPerformance.eq(costPerformance);
    }

    private BooleanExpression eqCanEatSingle(Boolean canEatSingle) {
        if (canEatSingle == null) {
            return null;
        }
        return restaurant.canEatSingle.eq(canEatSingle);
    }

    private BooleanExpression eqIsAtmosphere(Boolean isAtmosphere) {
        if (isAtmosphere == null) {
            return null;
        }
        return restaurant.isAtmosphere.eq(isAtmosphere);
    }
}
