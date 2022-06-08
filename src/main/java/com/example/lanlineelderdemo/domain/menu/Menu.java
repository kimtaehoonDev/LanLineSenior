package com.example.lanlineelderdemo.domain.menu;

import com.example.lanlineelderdemo.domain.restaurant.Restaurant;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id","openType","menuName","price"})
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @NotNull
    @Enumerated(EnumType.STRING)
    private OpenType openType;

    @NotBlank
    private String menuName;

    @NotNull
    private Integer numberOfMeal;

    @NotNull
    private Integer price;

    @Builder(builderClassName = "createMenu", builderMethodName = "createMenu")
    private Menu(Restaurant restaurant, OpenType openType, String menuName, Integer numberOfMeal, Integer price) {
        validate(restaurant, openType, menuName, numberOfMeal, price);
        this.restaurant = restaurant;
        this.openType = openType;
        this.menuName = menuName;
        this.numberOfMeal = numberOfMeal;
        this.price = price;
    }

    private void validate(Restaurant restaurant, OpenType openType, String menuName, Integer numberOfMeal, Integer price) {
        if (restaurant == null) {
            throw new IllegalArgumentException("식당 정보가 누락되었습니다.");
        }
        if (openType == null) {
            throw new IllegalArgumentException("메뉴의 영업타입이 누락되었습니다.");
        }
        if (menuName == null) {
            throw new IllegalArgumentException("메뉴의 이름이 누락되었습니다.");
        }
        if (numberOfMeal == null) {
            throw new IllegalArgumentException("메뉴의 식사인원 수가 누락되었습니다.");
        }
        if (price == null) {
            throw new IllegalArgumentException("메뉴의 가격이 누락되었습니다.");
        }
    }

}
