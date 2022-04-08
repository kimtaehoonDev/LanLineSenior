package com.example.lanlineelderdemo.controller;

import com.example.lanlineelderdemo.domain.FoodCategory;
import com.example.lanlineelderdemo.domain.Location;
import com.example.lanlineelderdemo.domain.SearchCondition;
import com.example.lanlineelderdemo.service.RestaurantService;
import com.example.lanlineelderdemo.service.dto.response.RestaurantResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    /**
     * 검색
     */
    @GetMapping
    public void searchPage() {
        //DTO 만들어서 넣어준다.
        // 페이지1 검색 첫번째 화면을 만들어준다.
        // return templates/...
    }

    @PostMapping
    public String searchRestaurantsUsingSearchCondition(List<Location> locations, List<FoodCategory> unselectedCategories, Boolean isAtmosphere, Boolean hasCostPerformance, Boolean canEatSingle, Integer maxCostLine) { //searchRestaurantRequestDto
        SearchCondition searchCondition = new SearchCondition(locations, unselectedCategories, isAtmosphere, hasCostPerformance, canEatSingle, maxCostLine);
        List<RestaurantResponseDto> restaurantResponseDtos = restaurantService.searchRestaurantNames(searchCondition);
        //TODO return 타임리프로 지도에 매핑해주기. 저거 한개씩 다, 여기 화면은 검색2번째거 화면, GetMapping
        return "/result";
    }

    @GetMapping("/result")
    public void showRestaurants(List<RestaurantResponseDto> restaurantResponseDtos) {
        for (RestaurantResponseDto restaurantResponseDto : restaurantResponseDtos) {
            //TODO 지도 API 불러와서 마커를 찍는다.
            // 밑에 리스트 형태로 만든다. 각 리스트는 레스토랑 아이디를 가지고 있어서 클릭하면 해당 ~로 이동.
        }

    }

    /**
     * 상세정보
     */
    @GetMapping("/restaurant/{restaurantId}")
    public void showRestaurantDetail(Long restaurantId) {
        RestaurantResponseDto restaurantResponseDto = restaurantService.searchRestaurantByRestaurantId(restaurantId);
        //TODO 이후, 후기 기능 생기면 여기에 연결해서 달아주고, 같이 타임리프 안에 넣어주면 될 거 같음.
    }

    /**
     * 등록 페이지 GetMapping (Admin만)
     * TODO 로그인 페이지가 없는데, 어떻게 Admin인지 알지? IP를 사용해 따로 접근할 수 있게 만들어줘야할까? 그러면 웹서버가 두개가 필요한건가?
     */

    /**
     * 등록 (Admin만)
     * 201 created Ok / 이름이 같은 경우 에러 띄워주기
     * 타임리프를 사용하는 경우 http 상태코드 쓸 이유가 없잖아.
     */
    @PostMapping()
    public void registerRestaurantByAdmin(RegisterRestaurantRequestDto registerRestaurantRequestDto) {
        Long registerRestaurantId = restaurantService.registerRestaurant(registerRestaurantRequestDto.changeRequestServiceDto());
        //return type String, 그리고 GetMapping 같은 주소로 다시 보내주면 됨. 쉴새없이 등록하게.
    }

    /**
     * 수정
     * 수정 http 상태메세지 / 수정이 안되는 상황?
     */
}
