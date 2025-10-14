package com.groupTuAnh.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_profiles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfile {
    @Id
    @Column(name = "user_id")
    private Long userID;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "user_id")
    private User user;

    private String name;

    private String phone;

    private String address;

    private String avatar;

}