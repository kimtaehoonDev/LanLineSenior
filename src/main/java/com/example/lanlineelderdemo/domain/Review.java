package com.example.lanlineelderdemo.domain;

import com.example.lanlineelderdemo.domain.restaurant.Restaurant;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    private String content;

    private String writerName;

    private String password;

    private Boolean isUsing;

    @Builder(builderClassName = "createReview", builderMethodName = "createReview")
    private Review(Restaurant restaurant, String content, String writerName, String password) {
        // TODO 검증은 일단 DTO에서 먼저.
        this.restaurant = restaurant;
        this.content = content;
        this.writerName = writerName;
        this.password = password;
        this.isUsing = Boolean.TRUE;
    }

    private void validatePasswordIsSame(String password) {
        if (this.password.equals(password)) {
            return;
        }
        throw new IllegalArgumentException("비밀번호가 다릅니다.");
    }

    public void update(String writerName, String content, String password) {
        validatePasswordIsSame(password);
        if (writerName != null) {
            this.writerName = writerName;
        }
        if (content != null) {
            this.content = content;
        }
    }

    public void delete(String password) {
        validatePasswordIsSame(password);
        this.isUsing = Boolean.FALSE;
    }
}
