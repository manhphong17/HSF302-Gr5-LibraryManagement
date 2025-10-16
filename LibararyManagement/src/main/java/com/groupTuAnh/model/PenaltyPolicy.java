package com.groupTuAnh.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.groupTuAnh.enums.PenaltyType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "penalty_policies")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PenaltyPolicy implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Ngày chính sách bắt đầu có hiệu lực
    private LocalDate effectiveDate;

    // Phạt trễ hạn (tính theo ngày)
    private Double finePerDay;

    // Phạt mất sách (tỉ lệ % giá sách)
    private Double lostBookFineRate;

    // Kiểu phạt (LOST hoặc OVERDUE)
    @Enumerated(EnumType.STRING)
    private PenaltyType type;

    @OneToMany(mappedBy = "penaltyPolicy", cascade = CascadeType.ALL)
    private List<Penalty> penalties;


}
