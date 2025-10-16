package com.groupTuAnh.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookSearchRequest {
    private String keyword;       // title or author
    private String categoryName;
    private String sortBy;
}


