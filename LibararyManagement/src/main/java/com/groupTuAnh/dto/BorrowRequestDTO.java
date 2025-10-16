package com.groupTuAnh.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

@Data
public class BorrowRequestDTO {
    private String studentCode;
    private Long bookItemId;
    private Long librarianId;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate dueDate;
}
