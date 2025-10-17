package com.groupTuAnh.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "librarians")
@Getter @Setter
public class Librarian {

    @Id
    @Column(name = "account_id")
    private Long id; // chính là account_id

    @MapsId
    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(name = "staff_code")
    private String staffCode;

    @OneToMany(mappedBy = "librarian", cascade = CascadeType.ALL)
    private List<Transaction> transactions;
}