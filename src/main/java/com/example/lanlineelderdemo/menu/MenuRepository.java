package com.example.lanlineelderdemo.menu;

import com.example.lanlineelderdemo.domain.menu.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    List<Menu> findByRestaurant_Id(@Param(value = "restaurantId") Long restaurantId);
}
