package com.example.lanlineelderdemo.controller;

import com.example.lanlineelderdemo.domain.*;
import com.example.lanlineelderdemo.service.RestaurantService;
import com.example.lanlineelderdemo.service.dto.response.SearchRestaurantResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantService restaurantService;
    private final EnumMapper enumMapper;

    /**
     * 검색
     */
    @GetMapping
    public String searchPage(Model model) {
        SearchRestaurantRequestDto searchRestaurantRequestDto = new SearchRestaurantRequestDto();
        model.addAttribute("searchRestaurantRequestDto", searchRestaurantRequestDto);
        //여기서 잘못된듯. 제대로 꺼내 쓰기.

        Map<String, List<EnumValue>> enums = enumMapper.getAll();
//        List<EnumValue> locations = enums.get("locations");
//        List<EnumValue> foodCategories = enums.get("foodCategories");

//        model.addAttribute("locations", locations);
//        model.addAttribute("foodCategories", foodCategories);
        return "searchPage";
        // 검색 페이지 만들어주기.
    }

    @ModelAttribute("locations")
    public Map<String, String> locations() {
        Map<String, List<EnumValue>> enums = enumMapper.getAll();
        List<EnumValue> enumValues = enums.get("locations");

        Map<String, String> locations = new LinkedHashMap<>();
        for (EnumValue enumValue : enumValues) {
            locations.put(enumValue.getKey(), enumValue.getValue());
        }
        return locations;
    }

    @ModelAttribute("foodCategories")
    public Map<String, String> foodCategories() {
        Map<String, List<EnumValue>> enums = enumMapper.getAll();
        List<EnumValue> enumValues = enums.get("foodCategories");

        Map<String, String> foodCategories = new LinkedHashMap<>();
        for (EnumValue enumValue : enumValues) {
            foodCategories.put(enumValue.getKey(), enumValue.getValue());
        }
        return foodCategories;
    }

    @PostMapping
    public String searchRestaurantsUsingSearchCondition(
            @ModelAttribute SearchRestaurantRequestDto searchRestaurantRequestDto,
            RedirectAttributes re) {

        SearchCondition searchCondition = new SearchCondition(searchRestaurantRequestDto.getLocations(),
                searchRestaurantRequestDto.getUnselectedCategories(), searchRestaurantRequestDto.getIsAtmosphere(),
                searchRestaurantRequestDto.getHasCostPerformance(), searchRestaurantRequestDto.getCanEatSingle(),
                searchRestaurantRequestDto.getMaxCostLine());
        List<SearchRestaurantResponseDto> searchRestaurantResponseDtos = restaurantService.searchRestaurantNames(searchCondition);
        SearchRestaurantResponseDtos result = new SearchRestaurantResponseDtos(searchRestaurantResponseDtos);

        re.addFlashAttribute("result", result);
        return "redirect:/result";
    }

    @GetMapping("/result")
    public String showRestaurants(@ModelAttribute(name = "result") SearchRestaurantResponseDtos result) {
        List<SearchRestaurantResponseDto> searchRestaurantResponseDtos = result.searchRestaurantResponseDtos;
        for (SearchRestaurantResponseDto searchRestaurantResponseDto : searchRestaurantResponseDtos) {
//            TODO 지도 API 불러와서 마커를 찍는다.
//             밑에 리스트 형태로 만든다. 각 리스트는 레스토랑 아이디를 가지고 있어서 클릭하면 해당 ~로 이동.
            System.out.println(searchRestaurantResponseDto);
        }
    return "hello";
        //전체 데이터 보여주는 ~~ 만들기. naver api 참고하기.
    }

    /**
     * 상세정보
     */
    @GetMapping("/restaurant/{restaurantId}")
    public String showRestaurantDetail(Long restaurantId) {
        SearchRestaurantResponseDto searchRestaurantResponseDto = restaurantService.searchRestaurantByRestaurantId(restaurantId);
        //TODO 이후, 후기 기능 생기면 여기에 연결해서 달아주고, 같이 타임리프 안에 넣어주면 될 거 같음.
        return "hello";
        // 식당의 상세정보 보여주는 페이지를 만들기.
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
//    @PostMapping()
//    public String registerRestaurantByAdmin(RegisterRestaurantRequestDto registerRestaurantRequestDto) {
//        Long registerRestaurantId = restaurantService.registerRestaurant(registerRestaurantRequestDto.changeRequestServiceDto());
//        return type String, 그리고 GetMapping 같은 주소로 다시 보내주면 됨. 쉴새없이 등록하게.
//        return "hello";
//    }

    /**
     * 수정
     * 수정 http 상태메세지 / 수정이 안되는 상황?
     */
}
