package com.example.lanlineelderdemo.web.form.review;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ReviewCreateForm {
    @NotBlank
    private String name;
    @NotBlank
    private String password;
    @NotBlank
    private String content;

}
