package com.example.lanlineelderdemo.restaurant.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GeoLocation {
    private double locationX;
    private double locationY;

    public GeoLocation(double locationX, double locationY) {
        this.locationX = locationX;
        this.locationY = locationY;
    }
}
