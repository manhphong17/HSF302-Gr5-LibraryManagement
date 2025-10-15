package com.groupTuAnh.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "librarians")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Librarian extends Account {
    private String name;

    private String staffCode;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private UserProfile userProfile;

    @OneToMany(mappedBy = "librarian", cascade = CascadeType.ALL)
    private List<Transaction> transactions;

}