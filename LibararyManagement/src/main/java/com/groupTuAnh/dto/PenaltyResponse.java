package com.groupTuAnh.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PenaltyResponse {
    private Long penaltyId;
    private String type;         // LOST or OVERDUE
    private double totalFine;
    private boolean paid;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
}


