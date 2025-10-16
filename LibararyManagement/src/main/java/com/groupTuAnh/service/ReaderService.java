package com.groupTuAnh.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.groupTuAnh.dto.BookBorrowedResponse;
import com.groupTuAnh.dto.PageResponse;
import com.groupTuAnh.dto.PenaltyResponse;
import com.groupTuAnh.dto.ReaderDetailsResponse;
import com.groupTuAnh.dto.ReaderSearchRequest;
import com.groupTuAnh.model.BorrowRecord;
import com.groupTuAnh.model.Reader;
import com.groupTuAnh.repository.ReaderRepository;
import com.groupTuAnh.repository.SearchRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReaderService {
    private final ReaderRepository readerRepository;
    private final SearchRepository searchRepository;

    public List<ReaderDetailsResponse> getAll() {
        List<Reader> readers = readerRepository.findAll();
        return null;
    }

    public PageResponse<?> getAllByMember(ReaderSearchRequest req) {
        return searchRepository.getReadersWithFilterByManyColumnAndSortBy(req);
    }

    public List<BookBorrowedResponse> getBorrowedBooks(Long accountId) {
        Reader reader = readerRepository.findByAccountId(accountId).orElseThrow(
                () -> new IllegalArgumentException("Reader not found with id: " + accountId));
        List<BorrowRecord> borrowRecords = reader.getBorrowRecords();

        return borrowRecords.stream()
                .map(borrowRecord -> BookBorrowedResponse.builder()
                        .barcode(borrowRecord.getBookItem().getBarCode())
                        .title(borrowRecord.getBookItem().getBook().getTitle())
                        .borrowDate(borrowRecord.getBorrowDate())
                        .returnDate(borrowRecord.getReturnDate())
                        .isReturned(borrowRecord.isReturned())
                        .build()).toList();

    }

    public List<PenaltyResponse> getPenalties(Long accountId) {
        Reader reader = readerRepository.findByAccountId(accountId).orElseThrow(
                () -> new IllegalArgumentException("Reader not found with id: " + accountId));

        return reader.getPenalties().stream()
                .map(p -> PenaltyResponse.builder()
                        .penaltyId(p.getPenaltyId())
                        .type(p.getTotalDaysLate() > 0 ? "OVERDUE" : "LOST")
                        .totalFine(p.getTotalFine())
                        .paid(p.isPaid())
                        .borrowDate(p.getRecord() != null ? p.getRecord().getBorrowDate() : null)
                        .dueDate(p.getRecord() != null ? p.getRecord().getDueDate() : null)
                        .returnDate(p.getRecord() != null ? p.getRecord().getReturnDate() : null)
                        .build())
                .toList();
    }

}
