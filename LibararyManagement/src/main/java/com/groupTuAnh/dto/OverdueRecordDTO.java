package com.groupTuAnh.dto;

import lombok.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OverdueRecordDTO {
    private Long recordId;
    private String bookTitle;
    private String readerName;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private int daysLate;
    private double totalFine;
    private boolean isPaid;
    private double fineSnapShot;
}