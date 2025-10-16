package com.groupTuAnh.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookBorrowedResponse {
    String title;
    String barcode;
    LocalDate borrowDate;
    LocalDate returnDate;
    boolean isReturned;
}
