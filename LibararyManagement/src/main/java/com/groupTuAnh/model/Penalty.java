package com.groupTuAnh.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "penalties")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Penalty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long penaltyId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "record_id")
    private BorrowRecord record;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Reader reader;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rate_id")
    private PenaltyRate penaltyRate;

    private double totalFine;

    private boolean isPaid = false;

    private double fineSnapshot;
}