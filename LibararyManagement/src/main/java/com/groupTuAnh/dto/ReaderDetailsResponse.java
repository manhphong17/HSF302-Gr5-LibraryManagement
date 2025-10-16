package com.groupTuAnh.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReaderDetailsResponse {
    String code;
    String name;
    String phone;
    String memberShipName;
    LocalDate expiryDate;
    double balance;
    double debt;
}
