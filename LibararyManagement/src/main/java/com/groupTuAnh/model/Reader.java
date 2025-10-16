package com.groupTuAnh.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "readers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@PrimaryKeyJoinColumn(name = "account_id")
public class Reader extends Account {
    private double balance = 0.0;

    private double debt = 0.0;

    private String studentCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "membership_package_id")
    private MembershipPackage membership;

    private LocalDate startDate = LocalDate.now();
    private LocalDate expMembership = LocalDate.now();


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private UserProfile userProfile;

    @OneToMany(mappedBy = "reader", cascade = CascadeType.ALL)
    private List<BorrowRecord> borrowRecords = new ArrayList<>();

    @OneToMany(mappedBy = "reader", cascade = CascadeType.ALL)
    private List<Transaction> transactions = new ArrayList<>();

    @OneToMany(mappedBy = "reader", cascade = CascadeType.ALL)
    private List<Penalty> penalties = new ArrayList<>();
}