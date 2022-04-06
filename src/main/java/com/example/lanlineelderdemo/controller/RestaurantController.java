package com.example.lanlineelderdemo.controller;

import com.example.lanlineelderdemo.domain.SearchCondition;
import com.example.lanlineelderdemo.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    /**
     * 검색
     * 검색을 하면 List<String>으로 이름을 받아옴. 그걸 naver api로 보내서 결과값을 받아올거. 그거랑 상태값 넣어서 return
     */
    @GetMapping()
    public void findRestaurantsUsingSearchCondition(SearchCondition searchCondition) {
        //TODO SearchCondiion을 도메인으로 만들어서 컨트,서비,레포에서 의존하게 만들면 어떨까
        List<String> restaurantNames = restaurantService.searchRestaurantNames(searchCondition);
//         TODO 찾아보니깐, 전화번호, 사진 등의 정보는 제공되지 않는다. 내가 별도의 디비를 만들어야 할까? 라는 생각이 든다.


    }

    /**
     * 등록
     * 201 created Ok / 이름이 같은 경우 에러 띄워주기
     */

    /**
     * 수정
     * 수정 http 상태메세지 / 수정이 안되는 상황?
     */
}
