package com.example.lanlineelderdemo.controller;

import com.example.lanlineelderdemo.EnumMapper;
import com.example.lanlineelderdemo.EnumValue;
import com.example.lanlineelderdemo.controller.dto.CreateReviewRequestDto;
import com.example.lanlineelderdemo.controller.dto.SearchRestaurantRequestDto;
import com.example.lanlineelderdemo.controller.dto.ShowRestaurantDetailsResponseDto;
import com.example.lanlineelderdemo.domain.*;
import com.example.lanlineelderdemo.domain.menu.OpenType;
import com.example.lanlineelderdemo.domain.restaurant.FoodCategory;
import com.example.lanlineelderdemo.domain.restaurant.Location;
import com.example.lanlineelderdemo.service.menu.MenuService;
import com.example.lanlineelderdemo.service.restaurant.RestaurantService;
import com.example.lanlineelderdemo.service.menu.RegisterMenuRequestServiceDto;
import com.example.lanlineelderdemo.service.restaurant.request.RegisterRestaurantRequestServiceDto;
import com.example.lanlineelderdemo.service.menu.findRecommendMenuInRestaurantDto;
import com.example.lanlineelderdemo.service.restaurant.response.SearchRestaurantResponseDto;
import com.example.lanlineelderdemo.service.restaurant.response.InqueryRestaurantResponseDto;
import com.example.lanlineelderdemo.service.review.ReviewService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
    private final RestaurantService restaurantService;
    private final MenuService menuService;
    private final ReviewService reviewService;
    private final EnumMapper enumMapper;

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
    @GetMapping
    public String searchRestaurantsForm(Model model) {
        SearchRestaurantRequestDto searchRestaurantRequestDto = new SearchRestaurantRequestDto();
        model.addAttribute("searchRestaurantRequestDto", searchRestaurantRequestDto);
        return "searchPage";
    }

    /**
     * 검색
     */
    @PostMapping("results")
    public String searchRestaurants(
            @ModelAttribute SearchRestaurantRequestDto searchRestaurantRequestDto, Model model) {
        SearchCondition searchCondition = searchRestaurantRequestDto.toEntity();
        List<SearchRestaurantResponseDto> results = restaurantService.searchRestaurants(searchCondition);
        model.addAttribute("results",results);
        return "resultPage";
    }

    /**
     * 상세정보
     */
    @GetMapping("/restaurants/{restaurantId}")
    public String showRestaurantDetails(@PathVariable Long restaurantId, Model model) {
        model.addAttribute("restaurant", makeShowRestaurantDetailsResponseDto(restaurantId));
        model.addAttribute("reviews", reviewService.inqueryRestaurantReviews(restaurantId));
        model.addAttribute("createReviewRequestDto", new CreateReviewRequestDto());
        return "detailPage";
        // 식당의 상세정보 보여주는 페이지를 만들기.
    }

    private ShowRestaurantDetailsResponseDto makeShowRestaurantDetailsResponseDto(Long restaurantId) {
        InqueryRestaurantResponseDto inqueryRestaurantResponse = restaurantService.inqueryRestaurant(restaurantId);
        findRecommendMenuInRestaurantDto recommendMenu = menuService.findRecommendMenuInRestaurant(restaurantId);
        ShowRestaurantDetailsResponseDto restaurant = ShowRestaurantDetailsResponseDto.create(inqueryRestaurantResponse, recommendMenu);
        return restaurant;
    }

    /**
     * 등록 페이지 GetMapping (Admin만) 엑셀 사용해서 그냥 등록해버리면 편할텐데. 이거 방법 찾아보기.
     * TODO 식당 한개씩 입력받는 기능도 만들어야 할지도. 일단 keep
     */
    @GetMapping("restaurants/new")
    public String registerRestaurantForm(@ModelAttribute MultipartFile file) {
        return "registerPage";
    }

    /**
     * 등록 (Admin만)
     * 201 created Ok / 이름이 같은 경우 에러 띄워주기
     * 타임리프를 사용하는 경우 http 상태코드 쓸 이유가 없잖아.
     */
    @PostMapping("/restaurants")
    public String registerRestaurantByAdmin(@ModelAttribute MultipartFile file) throws IOException{
        Workbook workbook = makeWorkbook(file);
        List<RegisterRestaurantRequestServiceDto> dataList = makeRegisterRestaurantRequestDtoUsingEachColumn(workbook);
        restaurantService.registerRestaurants(dataList);
        return "redirect:/";
    }

    private List<RegisterRestaurantRequestServiceDto> makeRegisterRestaurantRequestDtoUsingEachColumn(Workbook workbook) {
        Sheet worksheet = workbook.getSheetAt(0); //아마 설명하는 가장 위칸?

        List<RegisterRestaurantRequestServiceDto> dataList = new ArrayList<>();
        for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) { // 4
            Row row = worksheet.getRow(i);
            RegisterRestaurantRequestServiceDto registerRestaurantRequestServiceDto = new RegisterRestaurantRequestServiceDto();

            registerRestaurantRequestServiceDto.setName(row.getCell(0).getStringCellValue());
            registerRestaurantRequestServiceDto.setGeoLocationX(row.getCell(1).getNumericCellValue());
            registerRestaurantRequestServiceDto.setGeoLocationY(row.getCell(2).getNumericCellValue());
            registerRestaurantRequestServiceDto.setLocation(Location.find(row.getCell(3).getStringCellValue()));
            registerRestaurantRequestServiceDto.setCategory(FoodCategory.find(row.getCell(4).getStringCellValue()));
            registerRestaurantRequestServiceDto.setIsAtmosphere(row.getCell(5).getBooleanCellValue());
            registerRestaurantRequestServiceDto.setHasCostPerformance(row.getCell(6).getBooleanCellValue());
            registerRestaurantRequestServiceDto.setCanEatSingle(row.getCell(7).getBooleanCellValue());
            if (row.getCell(8) != null) {
                registerRestaurantRequestServiceDto.setAdminComment(row.getCell(8).getStringCellValue());
            }
            registerRestaurantRequestServiceDto.setUrl(row.getCell(9).getStringCellValue());
            dataList.add(registerRestaurantRequestServiceDto);
        }
        return dataList;
    }

    /**
     * 메뉴 등록 페이지 GetMapping (Admin만) 엑셀 사용해서 그냥 등록해버리면 편할텐데. 이거 방법 찾아보기.
     */
    @GetMapping("/menu/new")
    public String registerMenuForm(@ModelAttribute MultipartFile file) {
        return "registerMenuPage";
    }

    /**
     * 메뉴 등록 (Admin만)
     */
    @PostMapping("/menu")
    public String registerMenuByAdmin(@ModelAttribute MultipartFile file) throws IOException{
        Workbook workbook = makeWorkbook(file);
        List<RegisterMenuRequestServiceDto> dataList = makeMenuRequestServiceDtoUsingEachColumn(workbook);
        menuService.registerMenus(dataList);
        return "redirect:/";
    }

    private List<RegisterMenuRequestServiceDto> makeMenuRequestServiceDtoUsingEachColumn(Workbook workbook) {
        Sheet worksheet = workbook.getSheetAt(0); //아마 설명하는 가장 위칸?
        List<RegisterMenuRequestServiceDto> dataList = new ArrayList<>();
        for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) { // 4
            Row row = worksheet.getRow(i);
            RegisterMenuRequestServiceDto registerMenuRequestServiceDto = new RegisterMenuRequestServiceDto();
            registerMenuRequestServiceDto.setRestaurantName(row.getCell(0).getStringCellValue());
            registerMenuRequestServiceDto.setOpenType(OpenType.find(row.getCell(1).getStringCellValue()));
            registerMenuRequestServiceDto.setMenuName(row.getCell(2).getStringCellValue());
            registerMenuRequestServiceDto.setNumberOfMeal((int) row.getCell(3).getNumericCellValue());
            registerMenuRequestServiceDto.setPrice((int) row.getCell(4).getNumericCellValue());

            dataList.add(registerMenuRequestServiceDto);
        }
        return dataList;

    }

    private Workbook makeWorkbook(MultipartFile file) throws IOException {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        if (!extension.equals("xlsx") && !extension.equals("xls")) {
            throw new IOException("엑셀파일만 업로드 해주세요.");
        }
        Workbook workbook = null;
        if (extension.equals("xlsx")) {
            workbook = new XSSFWorkbook(file.getInputStream());
        } else if (extension.equals("xls")) {
            workbook = new HSSFWorkbook(file.getInputStream());
        }
        return workbook;
    }


    /**
     * 수정
     * 수정 http 상태메세지 / 수정이 안되는 상황?
     */
}
