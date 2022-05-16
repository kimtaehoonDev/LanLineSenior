package com.example.lanlineelderdemo.web.form.review;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ReviewDeleteForm {
    @NotBlank //TODO 그외에 이상한 sql 공격 막을 수 있게 만들기.
    private String password;
}
