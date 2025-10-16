package com.groupTuAnh.dto;

public record DepositRequest(Long readerId, Long librarianId, Double amount, String note) {
}
