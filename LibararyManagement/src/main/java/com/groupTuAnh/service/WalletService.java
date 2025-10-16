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
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class WalletService {

    private final ReaderRepository readerRepository;
    private final LibrarianRepository librarianRepository;
    private final TransactionRepository transactionRepository;

    @Transactional
    public Transaction deposit(DepositRequest request) {
        if (request.amount() == null || request.amount() <= 0) {
            throw new IllegalArgumentException("Amount must be greater than 0");
        }

        Reader reader = readerRepository.findById(request.readerId())
                .orElseThrow(() -> new IllegalArgumentException("Reader not found"));

        Librarian librarian = librarianRepository.findById(request.librarianId())
                .orElseThrow(() -> new IllegalArgumentException("Librarian not found"));

        double currentDebt = reader.getDebt();
        double updatedBalance = reader.getBalance() + request.amount();
        reader.setBalance(updatedBalance);
        if (currentDebt > 0) {
            double payment = Math.min(reader.getBalance(), currentDebt);
            reader.setDebt(currentDebt - payment);
            reader.setBalance(reader.getBalance() - payment);
        }

        Transaction transaction = Transaction.builder()
                .reader(reader)
                .librarian(librarian)
                .type(TransactionType.DEPOSIT)
                .amount(request.amount())
                .date(LocalDate.now())
                .description(request.note())
                .build();

        return transactionRepository.save(transaction);
    }
}
