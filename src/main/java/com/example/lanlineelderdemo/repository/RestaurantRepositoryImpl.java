package com.example.lanlineelderdemo.repository;

import com.example.lanlineelderdemo.domain.Location;
import com.example.lanlineelderdemo.domain.QRestaurant;
import com.example.lanlineelderdemo.domain.Restaurant;
import com.example.lanlineelderdemo.domain.SearchCondition;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;

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
    public List<Restaurant> findRestaurantBySearchCondition(SearchCondition searchCondition) {

        //TODO 검증 해줘야함. 코드만 지금 작성한 상태.
        // TODO 체크 안한건(false처리) null로 들어가서 무시해줘야함. 분위기? x 했다고 분위기 없는 식당만 추천하는 게 아님.
        return queryFactory.select(restaurant)
                .from(restaurant)
                .where(includeLocations(searchCondition.getLocations()),
                        restaurant.category.notIn(searchCondition.getUnselectedCategories()),
                        eqIsAtmosphere(searchCondition.getIsAtmosphere()),
                        eqCanEatSingle(searchCondition.getCanEatSingle()),
                        eqHasCostPerformance(searchCondition.getHasCostPerformance()))
                .orderBy(NumberExpression.random().asc())
                .limit(5)
                .fetch();
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
        return restaurant.isAtmosphere.eq(costPerformance);
    }

    private BooleanExpression eqCanEatSingle(Boolean canEatSingle) {
        if (canEatSingle == null) {
            return null;
        }
        return restaurant.isAtmosphere.eq(canEatSingle);
    }

    private BooleanExpression eqIsAtmosphere(Boolean isAtmosphere) {
        if (isAtmosphere == null) {
            return null;
        }
        return restaurant.isAtmosphere.eq(isAtmosphere);
    }
}
