package com.groupTuAnh.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "membership_packages")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MembershipPackage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long packageId;

    private String name;

    private double price;

    private int maxBooksPerMonth;

    @OneToMany(mappedBy = "membership", cascade = CascadeType.ALL)
    private List<Reader> readers = new ArrayList<>();
}