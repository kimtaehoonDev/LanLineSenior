package com.example.lanlineelderdemo.restaurant;

import com.example.lanlineelderdemo.domain.restaurant.Restaurant;
import com.example.lanlineelderdemo.domain.SearchCondition;
import com.example.lanlineelderdemo.restaurant.repository.dto.FindRestaurantBySearchConditionResponseDto;
import com.example.lanlineelderdemo.restaurant.repository.RestaurantRepository;
import com.example.lanlineelderdemo.restaurant.dto.service.RestaurantCreateServiceRequestDto;
import com.example.lanlineelderdemo.restaurant.dto.service.SearchRestaurantResponseDto;
import com.example.lanlineelderdemo.restaurant.dto.service.RestaurantResponseDto;
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
    public void registerRestaurants(List<RestaurantCreateServiceRequestDto> dataList) {
        for (RestaurantCreateServiceRequestDto data : dataList) {
            registerRestaurant(data);
        }
    }

    @Transactional
    public Long registerRestaurant(RestaurantCreateServiceRequestDto restaurantCreateServiceRequestDto) {
        validateRestaurantNameDuplicate(restaurantCreateServiceRequestDto);
        Restaurant restaurant = restaurantCreateServiceRequestDto.toEntity();
        restaurantRepository.save(restaurant);
        return restaurant.getId();
    }

    private void validateRestaurantNameDuplicate(RestaurantCreateServiceRequestDto restaurantCreateServiceRequestDto) {
        if (!restaurantRepository.findByName(restaurantCreateServiceRequestDto.getName()).isEmpty()) {
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
    public List<SearchRestaurantResponseDto> searchRestaurants(SearchCondition searchCondition) {
        List<FindRestaurantBySearchConditionResponseDto> findRestaurants = restaurantRepository.findRestaurantBySearchCondition(searchCondition);
        return findRestaurants.stream().map(findRestaurantBySearchConditionResponseDto -> SearchRestaurantResponseDto.of(findRestaurantBySearchConditionResponseDto)).collect(Collectors.toList());
    }

    /**
     * 상세정보
     * @return
     */
    public RestaurantResponseDto inqueryRestaurant(Long restaurantId) {
        Restaurant restaurant = findRestaurantByRestaurantId(restaurantId);
        return RestaurantResponseDto.of(restaurant);
    }

    private Restaurant findRestaurantByRestaurantId(Long restaurantId) {
        Optional<Restaurant> parsingRestaurant = restaurantRepository.findById(restaurantId);
        if (parsingRestaurant.isEmpty()) {
            throw new IllegalArgumentException("해당 restaurant는 존재하지 않습니다.");
        }
        return parsingRestaurant.get();
    }
}
