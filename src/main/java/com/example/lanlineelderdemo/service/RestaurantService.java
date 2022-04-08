package com.example.lanlineelderdemo.service;

import com.example.lanlineelderdemo.domain.Restaurant;
import com.example.lanlineelderdemo.domain.SearchCondition;
import com.example.lanlineelderdemo.repository.RestaurantRepository;
import com.example.lanlineelderdemo.service.dto.request.RegisterRequestServiceDto;
import com.example.lanlineelderdemo.service.dto.response.RestaurantResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;

    /**
     * CREATE
     * 어드민 페이지 -> 식당 등록 기능
     */
    @Transactional
    public Long registerRestaurant(RegisterRequestServiceDto registerRequestServiceDto) {
        validateRestaurantNameDuplicate(registerRequestServiceDto);
        Restaurant restaurant = Restaurant.createRestaurant()
                .name(registerRequestServiceDto.getName())
                .location(registerRequestServiceDto.getLocation())
                .geoLocationX(registerRequestServiceDto.getGeoLocationX())
                .geoLocationY(registerRequestServiceDto.getGeoLocationY())
                .category(registerRequestServiceDto.getCategory())
                .isAtmosphere(registerRequestServiceDto.getIsAtmosphere())
                .hasCostPerformance(registerRequestServiceDto.getHasCostPerformance())
                .canEatSingle(registerRequestServiceDto.getCanEatSingle())
                .adminComment(registerRequestServiceDto.getAdminComment())
                .minCost(registerRequestServiceDto.getMinCost())
                .telNum(registerRequestServiceDto.getTelNum())
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
     * 가게 정보 수정은 나중에 만들어주자. 등록 검색보다 훨씬 우선순위가 낮음.
     */
//    @Transactional
//    public void updateRestaurant(Long restaurantId, UpdateRequestServiceDto updateRequestServiceDto) {
//        Restaurant findRestaurant = getRestaurantUsingId(restaurantId);
//        findRestaurant.update(updateRequestServiceDto.getLocation(), updateRequestServiceDto.getCategory(),
//                updateRequestServiceDto.getIsAtmosphere(), updateRequestServiceDto.getHasCostPerformance(),
//                updateRequestServiceDto.getCanEatSingle(), updateRequestServiceDto.getAdminComment(),
//                updateRequestServiceDto.getMinCost());
//    }
//
//    private Restaurant getRestaurantUsingId(Long restaurantId) {
//        Optional<Restaurant> wrappingRestaurant = restaurantRepository.findById(restaurantId);
//        if (wrappingRestaurant.isEmpty()) {
//            throw new IllegalArgumentException("해당 식당은 존재하지 않거나 삭제되었습니다.");
//        }
//        Restaurant findRestaurant = wrappingRestaurant.get();
//        return findRestaurant;
//    }

    /**
     * 삭제 Restaurant 만들 필요 있나.
     */

    /**
     * 읽기
     * 검색을 하면 조건에 맞는 가게들의 이름을 리스트로 보내준다.
     * 최대 5개. 너무 많은걸 보여줘도 의미없고, 5개까지가 네이버 API에서 사용할 수 있는 양.
     * @return
     */
    public List<RestaurantResponseDto> searchRestaurantNames(SearchCondition searchCondition) {
        List<Restaurant> findRestaurants = restaurantRepository.findRestaurantBySearchCondition(searchCondition);
        return findRestaurants.stream().map(restaurant -> RestaurantResponseDto.makeUsingRestaurant(restaurant)).collect(Collectors.toList());
    }

    /**
     * 상세정보
     * @return
     */
    public RestaurantResponseDto searchRestaurantByRestaurantId(Long restaurantId) {
        Restaurant restaurant = findRestaurantByRestaurantId(restaurantId);
        RestaurantResponseDto restaurantResponseDto = RestaurantResponseDto.makeUsingRestaurant(restaurant);
        return restaurantResponseDto;
    }

    private Restaurant findRestaurantByRestaurantId(Long restaurantId) {
        Optional<Restaurant> parsingRestaurant = restaurantRepository.findById(restaurantId);
        if (parsingRestaurant.isEmpty()) {
            throw new IllegalArgumentException("해당 restaurant는 존재하지 않습니다.");
        }
        return parsingRestaurant.get();
    }

}
