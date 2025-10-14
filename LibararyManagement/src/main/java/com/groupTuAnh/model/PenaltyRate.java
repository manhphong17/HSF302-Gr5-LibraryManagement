package com.groupTuAnh.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "penalty_rates")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PenaltyRate implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    LocalDate effectiveDate;

    Double finePerDay;

    @OneToMany(mappedBy = "penaltyRate", cascade = CascadeType.ALL)
    private List<Penalty> penalties = new ArrayList<>();
}
