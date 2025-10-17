package com.groupTuAnh.service;

import com.groupTuAnh.dto.DepositRequest;
import com.groupTuAnh.enums.TransactionType;
import com.groupTuAnh.model.Librarian;
import com.groupTuAnh.model.Reader;
import com.groupTuAnh.model.Transaction;
import com.groupTuAnh.repository.LibrarianRepository;
import com.groupTuAnh.repository.ReaderRepository;
import com.groupTuAnh.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class WalletService {

    private final ReaderRepository readerRepository;
    private final LibrarianRepository librarianRepository;
    private final TransactionRepository transactionRepository;

    @Transactional
    public Transaction deposit(String studentCode, Long librarianId, Double amount, String note) {
        if (studentCode == null || studentCode.isBlank()) {
            throw new IllegalArgumentException("Vui lòng nhập mã sinh viên");
        }
        if (amount == null || amount <= 0) {
            throw new IllegalArgumentException("Số tiền nạp phải lớn hơn 0");
        }
        if (amount > 10_000_000) {
            throw new IllegalArgumentException("Số tiền nạp vượt giới hạn 10.000.000đ/lần");
        }

        Reader reader = readerRepository.findByStudentCode(studentCode)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy độc giả với mã: " + studentCode));

        Librarian librarian = librarianRepository.findByAccountAccountId(librarianId)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy Librarian"));

        // cập nhật số dư
        reader.setBalance(reader.getBalance() + amount);
        readerRepository.save(reader);

        Transaction tx = Transaction.builder()
                .reader(reader)
                .librarian(librarian)
                .type(com.groupTuAnh.enums.TransactionType.DEPOSIT)
                .amount(amount)                 // 'amount' kiểu double trong entity là OK
                .date(java.time.LocalDate.now())
                .description(note)
                .build();

        return transactionRepository.save(tx);
    }

}
