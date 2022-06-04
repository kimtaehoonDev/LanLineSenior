package com.example.lanlineelderdemo.review.dto;

import com.example.lanlineelderdemo.domain.Review;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class ReviewResponseDto {
    private Long id;
    private String writerName;
    private String content;
    private String lastModifiedDate;

    public ReviewResponseDto(Long id, String writerName, String content, LocalDateTime lastModifiedDate) {
        this.id = id;
        this.writerName = writerName;
        this.content = content;
        this.lastModifiedDate = lastModifiedDate.format(DateTimeFormatter.ofPattern("yyyy/MM/dd a HH:mm:ss"));

    }

    public static ReviewResponseDto from(Review review) {
        return new ReviewResponseDto(
                review.getId(), review.getWriterName(), review.getContent(), review.getLastModifiedDate());

    }
}
