package com.example.lanlineelderdemo.domain;

import com.example.lanlineelderdemo.domain.restaurant.Restaurant;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @NotBlank
    private String content;

    @NotBlank
    @Size(max = 10)
    private String writerName;

    @NotBlank
    private String password;

    @NotNull
    private Boolean isUsing;

    @Builder(builderClassName = "createReview", builderMethodName = "createReview")
    private Review(Restaurant restaurant, String content, String writerName, String password) {
        validate(restaurant, content, writerName, password);
        this.restaurant = restaurant;
        this.content = content;
        this.writerName = writerName;
        this.password = password;
        this.isUsing = Boolean.TRUE;
    }

    private void validate(Restaurant restaurant, String content, String writerName, String password) {
        if (restaurant == null) {
            throw new IllegalArgumentException("식당 정보가 누락되었습니다.");
        }
        if (content.isBlank()) {
            throw new IllegalArgumentException("댓글 내용이 누락되었습니다.");
        }
        if (writerName.isBlank()) {
            throw new IllegalArgumentException("작성자 이름이 누락되었습니다.");
        }
        if (password.isBlank()) {
            throw new IllegalArgumentException("비밀번호가 누락되었습니다.");
        }
    }

    public void update(String writerName, String content) {
        if (writerName != null) {
            this.writerName = writerName;
        }
        if (content != null) {
            this.content = content;
        }
    }

    public void delete() {
        this.isUsing = Boolean.FALSE;
    }
}
