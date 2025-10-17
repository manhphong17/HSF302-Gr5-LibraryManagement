package com.groupTuAnh.dto;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class LostPenaltyDTO {
    private Long penaltyId;
    private String readerName;
    private String bookTitle;
    private double totalFine;
    private boolean isPaid;
}
