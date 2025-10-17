package com.groupTuAnh.controller;

import com.groupTuAnh.dto.DepositRequest;
import com.groupTuAnh.model.Transaction;
import com.groupTuAnh.service.WalletService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/librarian/deposit")
public class DepositController {

    private final WalletService walletService;

    @GetMapping
    public String openDepositTab() {
        return "redirect:/dashboard#deposit";
    }

    @PostMapping
    public String doDeposit(@RequestParam String studentCode,
                            @RequestParam Long librarianId,
                            @RequestParam String amount,
                            @RequestParam(required = false) String note,
                            RedirectAttributes ra) {
        try {
            var amt = new java.math.BigDecimal(amount);

            // Validate min/max
            if (amt.compareTo(new java.math.BigDecimal("1000")) < 0 ||
                    amt.compareTo(new java.math.BigDecimal("10000000")) > 0) {
                ra.addFlashAttribute("depositError", "Số tiền phải từ 1.000 đến 10.000.000");
                ra.addFlashAttribute("depositDTO",
                        new com.groupTuAnh.dto.DepositRequest(studentCode, librarianId, amt, note));
                return "redirect:/dashboard#deposit";
            }

            // Gọi service: convert BigDecimal -> double
            double amtDouble = amt.doubleValue();
            Transaction trx = walletService.deposit(studentCode, librarianId, amtDouble, note);

            // Format message từ double (KHÔNG dùng toPlainString ở đây)
            // Cách 1: làm tròn về số nguyên tiền VNĐ
            long rounded = Math.round(trx.getAmount()); // getAmount() là double/Double
            ra.addFlashAttribute("depositMsg", "Nạp thành công: +" + rounded + " đ");

            // // Cách 2 (tuỳ): dùng BigDecimal để format đẹp
            // String amountStr = java.math.BigDecimal.valueOf(trx.getAmount())
            //        .setScale(0, java.math.RoundingMode.HALF_UP).toPlainString();
            // ra.addFlashAttribute("depositMsg", "Nạp thành công: +" + amountStr + " đ");

        } catch (RuntimeException ex) {
            ra.addFlashAttribute("depositError", ex.getMessage());
            ra.addFlashAttribute("depositDTO",
                    new com.groupTuAnh.dto.DepositRequest(studentCode, librarianId, safeBigDecimal(amount), note));
        }
        return "redirect:/dashboard#deposit";
    }

    private static java.math.BigDecimal safeBigDecimal(String s) {
        try { return new java.math.BigDecimal(s); } catch (Exception e) { return null; }
    }

}
