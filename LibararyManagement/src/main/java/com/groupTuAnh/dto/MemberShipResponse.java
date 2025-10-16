package com.groupTuAnh.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberShipResponse {
    Long id;
    String name;
    double price;
    int maxBookPerMonth;
}
