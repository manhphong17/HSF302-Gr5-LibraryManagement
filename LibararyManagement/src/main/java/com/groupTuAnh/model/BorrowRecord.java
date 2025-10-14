package com.groupTuAnh.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "borrow_records")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BorrowRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recordId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Reader reader;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    private LocalDate borrowDate;

    private LocalDate dueDate;

    private LocalDate returnDate;

    private boolean isReturned = false;

    @OneToMany(mappedBy = "record", cascade = CascadeType.ALL)
    private List<Penalty> penalties = new ArrayList<>();
}