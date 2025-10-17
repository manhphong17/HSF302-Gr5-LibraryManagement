// com.groupTuAnh.dto.DepositRequest
package com.groupTuAnh.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record DepositRequest(
        @NotBlank String studentCode,
        @NotNull  Long librarianId,
        @NotNull  @DecimalMin("1000") @DecimalMax("10000000")
        BigDecimal amount,
        @Size(max = 255) String note
) {}
