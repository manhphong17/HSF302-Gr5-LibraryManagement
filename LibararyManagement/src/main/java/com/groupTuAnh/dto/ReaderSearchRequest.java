package com.groupTuAnh.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReaderSearchRequest {
    @Builder.Default
    private int pageNo = 1;
    @Builder.Default
    private int pageSize = 10;
    private String keyword;
    private Long memberShipId;
    private Double minBalance;
    private Double maxBalance;
    private String sortBy;
}
