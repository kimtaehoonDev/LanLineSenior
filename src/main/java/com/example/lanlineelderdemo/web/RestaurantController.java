package com.example.lanlineelderdemo.web;

import com.example.lanlineelderdemo.web.form.review.ReviewCreateForm;
import com.example.lanlineelderdemo.utils.enums.EnumMapper;
import com.example.lanlineelderdemo.utils.enums.EnumValue;
import com.example.lanlineelderdemo.utils.ExcelFileManager;
import com.example.lanlineelderdemo.domain.SearchCondition;
import com.example.lanlineelderdemo.restaurant.dto.controller.ShowRestaurantDetailsResponseDto;
import com.example.lanlineelderdemo.domain.menu.OpenType;
import com.example.lanlineelderdemo.menu.MenuService;
import com.example.lanlineelderdemo.restaurant.RestaurantService;
import com.example.lanlineelderdemo.web.form.menu.MenuForm;
import com.example.lanlineelderdemo.restaurant.dto.service.RestaurantCreateServiceRequestDto;
import com.example.lanlineelderdemo.restaurant.dto.service.RestaurantRecommendMenuDto;
import com.example.lanlineelderdemo.restaurant.dto.service.SearchRestaurantResponseDto;
import com.example.lanlineelderdemo.restaurant.dto.service.RestaurantResponseDto;
import com.example.lanlineelderdemo.review.ReviewService;
import com.example.lanlineelderdemo.web.form.restaurant.SearchForm;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class RestaurantController {
    private final EnumMapper enumMapper;
    private final RestaurantService restaurantService;
    private final MenuService menuService;
    private final ReviewService reviewService;

    @ModelAttribute("locations")
    public Map<String, String> locations() {
        Map<String, List<EnumValue>> enums = enumMapper.get("locations");
        List<EnumValue> enumValues = enums.get("locations");

        Map<String, String> locations = new LinkedHashMap<>();
        for (EnumValue enumValue : enumValues) {
            locations.put(enumValue.getKey(), enumValue.getValue());
        }
        return locations;
    }

    @ModelAttribute("foodCategories")
    public Map<String, String> foodCategories() {
        Map<String, List<EnumValue>> enums = enumMapper.get("foodCategories");
        List<EnumValue> enumValues = enums.get("foodCategories");
        Map<String, String> foodCategories = new LinkedHashMap<>();
        for (EnumValue enumValue : enumValues) {
            foodCategories.put(enumValue.getKey(), enumValue.getValue());
        }
        return foodCategories;
    }

    @ModelAttribute("openTypes")
    public Map<String, String> openTypes() {
        Map<String, List<EnumValue>> enums = enumMapper.get("openTypes");
        List<EnumValue> enumValues = enums.get("openTypes");

        Map<String, String> foodCategories = new LinkedHashMap<>();
        for (EnumValue enumValue : enumValues) {
            if (enumValue.getKey() == OpenType.BOTH.getKey()) {
                continue;
            }
            foodCategories.put(enumValue.getKey(), enumValue.getValue());
        }
        return foodCategories;
    }

    /**
     * 검색 페이지
     */
    @GetMapping("/search")
    public String searchRestaurantsForm(@ModelAttribute("searchForm") SearchForm form) {
        return "restaurants/searchForm";
    }

    /**
     * 검색
     */
    @PostMapping("search")
    public String searchRestaurants(
            @ModelAttribute("searchForm") SearchForm searchForm, Model model) {
        SearchCondition searchCondition = searchForm.toEntity();
        List<SearchRestaurantResponseDto> results = restaurantService.searchRestaurants(searchCondition);
        model.addAttribute("results",results);
        return "restaurants/resultPage";
    }

    /**
     * 상세정보
     */
    @GetMapping("/restaurants/{restaurantId}")
    public String showRestaurantDetails(@PathVariable Long restaurantId, Model model,
                                        @ModelAttribute ReviewCreateForm reviewCreateForm) {
        model.addAttribute("restaurant", makeRestaurantDetailInfo(restaurantId));
        model.addAttribute("reviews", reviewService.inqueryRestaurantReviews(restaurantId));
        model.addAttribute("reviewCreateForm", reviewCreateForm);
        return "restaurants/detailPage";
        // 식당의 상세정보 보여주는 페이지를 만들기.
    }

    private ShowRestaurantDetailsResponseDto makeRestaurantDetailInfo(Long restaurantId) {
        RestaurantResponseDto inqueryRestaurantResponse = restaurantService.inqueryRestaurant(restaurantId);
        RestaurantRecommendMenuDto recommendMenu = menuService.findRestaurantRecommendMenu(restaurantId);
        ShowRestaurantDetailsResponseDto restaurant = ShowRestaurantDetailsResponseDto.create(inqueryRestaurantResponse, recommendMenu);
        return restaurant;
    }

    /**
     * 등록 페이지 GetMapping (Admin만) 엑셀 사용해서 그냥 등록해버리면 편할텐데. 이거 방법 찾아보기.
     * TODO 식당 한개씩 입력받는 기능도 만들어야 할지도. 일단 keep
     */
    @GetMapping("/restaurants/new")
    public String registerRestaurantForm(@ModelAttribute MultipartFile file) {
        return "restaurants/registerForm";
    }

    /**
     * 등록 (Admin만)
     * 201 created Ok / 이름이 같은 경우 에러 띄워주기
     * 타임리프를 사용하는 경우 http 상태코드 쓸 이유가 없잖아.
     */
    @PostMapping("/restaurants")
    public String registerRestaurantByAdmin(@ModelAttribute MultipartFile file) throws IOException{
        Sheet worksheet = ExcelFileManager.validateExcelFileIsAvailable(file);

        List<RestaurantCreateServiceRequestDto> dataList = new ArrayList<>();
        for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) {
            Row row = worksheet.getRow(i);
            dataList.add(new RestaurantCreateServiceRequestDto(row));
        }
        restaurantService.registerRestaurants(dataList);
        return "redirect:/";
    }

    /**
     * 메뉴 등록 페이지 GetMapping (Admin만) 엑셀 사용해서 그냥 등록해버리면 편할텐데. 이거 방법 찾아보기.
     */
    @GetMapping("/menu/new")
    public String registerMenuForm(@ModelAttribute MultipartFile file) {
        return "registerMenuForm";
    }

    /**
     * 메뉴 등록 (Admin만)
     */
    @PostMapping("/menu")
    public String registerMenus(@ModelAttribute MultipartFile file) throws IOException{
        List<MenuForm> dataList = new ArrayList<>();
        Sheet worksheet = ExcelFileManager.validateExcelFileIsAvailable(file);
        for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) { // 4
            Row row = worksheet.getRow(i);
            dataList.add(new MenuForm(row));
            //TODO 이걸 놔둔 이유는 벌크로 연산이 가능하도록.
            // 만약 그게 불가능하면 여기 위치에서 menuService.registerMenu가 맞음
        }
        menuService.registerMenus(dataList);
        return "redirect:/";
    }

    /**
     * 수정
     * 수정 http 상태메세지 / 수정이 안되는 상황?
     */
}
