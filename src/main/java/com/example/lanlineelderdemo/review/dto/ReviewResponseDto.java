package com.example.lanlineelderdemo.review.dto;

import com.example.lanlineelderdemo.domain.Review;
import lombok.Data;

@Data
public class ReviewResponseDto {
    private Long id;
    private String writerName;
    private String content;

    public ReviewResponseDto(Long id, String writerName, String content) {
        this.id = id;
        this.writerName = writerName;
        this.content = content;
    }

    public static ReviewResponseDto from(Review review) {
        return new ReviewResponseDto(
                review.getId(), review.getWriterName(), review.getContent());

    }
}
