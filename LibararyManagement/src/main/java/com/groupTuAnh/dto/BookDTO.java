package com.groupTuAnh.dto;

import java.util.List;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDTO {
    private Long bookId;
    private String title;
    private int stockQuantity;
    private List<Long> authorIds;
    private List<Long> categoryIds;
}