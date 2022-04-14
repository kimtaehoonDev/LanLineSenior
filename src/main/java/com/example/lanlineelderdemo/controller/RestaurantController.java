package com.example.lanlineelderdemo.controller;

import com.example.lanlineelderdemo.domain.*;
import com.example.lanlineelderdemo.service.RestaurantService;
import com.example.lanlineelderdemo.service.dto.request.RegisterRequestServiceDto;
import com.example.lanlineelderdemo.service.dto.response.SearchRestaurantResponseDto;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
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
    private final EnumMapper enumMapper;

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

    /**
     * 검색 페이지
     */
    @GetMapping
    public String searchPage(Model model) {
        SearchRestaurantRequestDto searchRestaurantRequestDto = new SearchRestaurantRequestDto();
        model.addAttribute("searchRestaurantRequestDto", searchRestaurantRequestDto);
        return "searchPage";
    }

    /**
     * 검색
     */
    @PostMapping("result")
    public String searchRestaurantsUsingSearchCondition(
            @ModelAttribute SearchRestaurantRequestDto searchRestaurantRequestDto, Model model) {

        SearchCondition searchCondition = new SearchCondition(searchRestaurantRequestDto.getLocations(),
                searchRestaurantRequestDto.getUnselectedCategories(), searchRestaurantRequestDto.getIsAtmosphere(),
                searchRestaurantRequestDto.getHasCostPerformance(), searchRestaurantRequestDto.getCanEatSingle(),
                searchRestaurantRequestDto.getMaxCostLine());
        List<SearchRestaurantResponseDto> results = restaurantService.searchRestaurant(searchCondition);
        model.addAttribute("results",results);//앞에 함수 결과를 받는다.
        return "resultPage";
    }

    /**
     * 상세정보
     */
    @GetMapping("/restaurants/{restaurantId}")
    public String showRestaurantDetail(@PathVariable Long restaurantId, Model model) {
        SearchRestaurantResponseDto restaurant = restaurantService.searchRestaurantByRestaurantId(restaurantId);
        //TODO 이후, 후기 기능 생기면 여기에 연결해서 달아주고, 같이 타임리프 안에 넣어주면 될 거 같음.
        model.addAttribute("restaurant", restaurant);
        return "detailPage";
        // 식당의 상세정보 보여주는 페이지를 만들기.
    }

    /**
     * 등록 페이지 GetMapping (Admin만) 엑셀 사용해서 그냥 등록해버리면 편할텐데. 이거 방법 찾아보기.
     * TODO 식당 한개씩 입력받는 기능도 만들어야 할지도. 일단 keep
     */
    @GetMapping("restaurants/new")
    public String registerPage(@ModelAttribute MultipartFile file) {
        return "registerPage";
    }

    /**
     * 등록 (Admin만)
     * 201 created Ok / 이름이 같은 경우 에러 띄워주기
     * 타임리프를 사용하는 경우 http 상태코드 쓸 이유가 없잖아.
     */
    @PostMapping("/restaurants")
    public String registerRestaurantByAdmin(@ModelAttribute MultipartFile file) throws IOException{
        List<RegisterRequestServiceDto> dataList = new ArrayList<>();

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

        Sheet worksheet = workbook.getSheetAt(0); //아마 설명하는 가장 위칸?
        DataFormatter formatter = new DataFormatter();

        for (int i = 1; i < worksheet.getPhysicalNumberOfRows(); i++) { // 4

            Row row = worksheet.getRow(i);
            RegisterRequestServiceDto registerRequestServiceDto = new RegisterRequestServiceDto();
            registerRequestServiceDto.setName(row.getCell(0).getStringCellValue());
            registerRequestServiceDto.setGeoLocationX(row.getCell(1).getNumericCellValue());
            registerRequestServiceDto.setGeoLocationY(row.getCell(2).getNumericCellValue());
            registerRequestServiceDto.setLocation(Location.find(row.getCell(3).getStringCellValue()));
            registerRequestServiceDto.setCategory(FoodCategory.find(row.getCell(4).getStringCellValue()));
            registerRequestServiceDto.setIsAtmosphere(row.getCell(5).getBooleanCellValue());
            registerRequestServiceDto.setHasCostPerformance(row.getCell(6).getBooleanCellValue());
            registerRequestServiceDto.setCanEatSingle(row.getCell(7).getBooleanCellValue());
            registerRequestServiceDto.setAdminComment(row.getCell(8).getStringCellValue());
            registerRequestServiceDto.setMinCost((int) row.getCell(9).getNumericCellValue());
            registerRequestServiceDto.setTelNum(formatter.formatCellValue(row.getCell(10)));
            registerRequestServiceDto.setAddress(row.getCell(11).getStringCellValue());
            registerRequestServiceDto.setUrl(row.getCell(12).getStringCellValue());

            dataList.add(registerRequestServiceDto);
        }

        restaurantService.registerRestaurants(dataList);
        return "redirect:/";
    }

    /**
     * 수정
     * 수정 http 상태메세지 / 수정이 안되는 상황?
     */
}
