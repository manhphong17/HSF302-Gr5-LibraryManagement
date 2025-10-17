package com.groupTuAnh.dto;

import lombok.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class BorrowRecordDTO {
    private Long recordId;
    private String studentCode;
    private String bookTitle;
    private String barCode;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private boolean isReturned;
    private boolean isOverdue; // <- tính trong query hoặc logic

}
