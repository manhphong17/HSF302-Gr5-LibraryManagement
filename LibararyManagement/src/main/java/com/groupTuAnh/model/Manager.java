package com.groupTuAnh.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "managers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Manager extends User {
    private String name;

    private String employeeId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserProfile userProfile;

    @OneToMany(mappedBy = "manager", cascade = CascadeType.ALL)
    private List<Transaction> transactions = new ArrayList<>();
}