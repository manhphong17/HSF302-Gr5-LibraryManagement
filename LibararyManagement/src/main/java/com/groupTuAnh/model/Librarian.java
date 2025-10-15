package com.groupTuAnh.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "librarians")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Librarian extends Account {

    private String staffCode;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private UserProfile userProfile;

    @OneToMany(mappedBy = "librarian", cascade = CascadeType.ALL)
    private List<Transaction> transactions;

}