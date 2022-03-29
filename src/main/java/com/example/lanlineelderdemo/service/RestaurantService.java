package com.example.lanlineelderdemo.service;

import com.example.lanlineelderdemo.domain.Restaurant;
import com.example.lanlineelderdemo.repository.RestaurantRepository;
import com.example.lanlineelderdemo.service.dto.RegisterRequestServiceDto;
import com.example.lanlineelderdemo.service.dto.SearchRequestServiceDto;
import com.example.lanlineelderdemo.service.dto.UpdateRequestServiceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
@RequiredArgsConstructor
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;

    /**
     * CREATE
     * 어드민 페이지 -> 식당 등록 기능
     */
    public Long registerRestaurant(RegisterRequestServiceDto registerRequestServiceDto) {
        validateRestaurantNameDuplicate(registerRequestServiceDto);
        Restaurant restaurant = Restaurant.createRestaurant()
                .name(registerRequestServiceDto.getName())
                .location(registerRequestServiceDto.getLocation())
                .category(registerRequestServiceDto.getCategory())
                .isAtmosphere(registerRequestServiceDto.getIsAtmosphere())
                .hasCostPerformance(registerRequestServiceDto.getHasCostPerformance())
                .canEatSingle(registerRequestServiceDto.getCanEatSingle())
                .adminComment(registerRequestServiceDto.getAdminComment())
                .minCost(registerRequestServiceDto.getMinCost())
                .maxCost(registerRequestServiceDto.getMaxCost())
                .build();
        restaurantRepository.save(restaurant);
        return restaurant.getId();
    }

    private void validateRestaurantNameDuplicate(RegisterRequestServiceDto registerRequestServiceDto) {
        if (restaurantRepository.findByName(registerRequestServiceDto.getName()).isEmpty()) {
            throw new IllegalStateException("같은 이름으로 이미 가게가 등록되어 있습니다.");
        }
    }

    /**
     * UPDATE
     * 어드민 페이지 -> 식당 정보 수정 기능
     */
    public void updateRestaurant(Long restaurantId, UpdateRequestServiceDto updateRequestServiceDto) {
        Restaurant findRestaurant = getRestaurantUsingId(restaurantId);
        findRestaurant.update(updateRequestServiceDto.getLocation(), updateRequestServiceDto.getCategory(),
                updateRequestServiceDto.getIsAtmosphere(), updateRequestServiceDto.getHasCostPerformance(),
                updateRequestServiceDto.getCanEatSingle(), updateRequestServiceDto.getAdminComment(),
                updateRequestServiceDto.getMinCost(), updateRequestServiceDto.getMaxCost());
    }

    private Restaurant getRestaurantUsingId(Long restaurantId) {
        Optional<Restaurant> wrappingRestaurant = restaurantRepository.findById(restaurantId);
        if (wrappingRestaurant.isEmpty()) {
            throw new IllegalArgumentException("해당 식당은 존재하지 않거나 삭제되었습니다.");
        }
        Restaurant findRestaurant = wrappingRestaurant.get();
        return findRestaurant;
    }

    /**
     * 삭제 Restaurant 만들 필요 있나.
     */

    /**
     * 읽기
     * 검색을 하면 조건에 맞는 가게들의 이름을 리스트로 보내준다.
     * 최대 5개. 너무 많은걸 보여줘도 의미없고, 5개까지가 네이버 API에서 사용할 수 있는 양.
     */
    public List<String> searchRestaurants(SearchRequestServiceDto searchRequestServiceDto) {

        // 동적쿼리로 검색을 한다. 결과를 5개만 받아온다. 이건 Repository에서 만들어야 하는 기능이다.
        // 가게 이름만 받아오자.
        return null;
    }

}
