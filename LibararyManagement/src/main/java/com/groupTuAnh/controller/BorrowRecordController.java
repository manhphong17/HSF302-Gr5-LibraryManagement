package com.groupTuAnh.controller;



import com.groupTuAnh.dto.BorrowRecordDTO;
import com.groupTuAnh.model.BorrowRecord;
import com.groupTuAnh.model.Penalty;
import com.groupTuAnh.service.BorrowRecordService;
import com.groupTuAnh.service.PenaltyService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import com.groupTuAnh.dto.OverdueRecordDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequiredArgsConstructor
class BorrowRecordController {
    private final BorrowRecordService borrowRecordService;
    private final PenaltyService penaltyService;

    @GetMapping("/overdue-records")
    public String getAllOverdueRecords(
            @RequestParam(required = false, defaultValue = "ALL") String status,
            Model model) {

        Boolean isPaid = null;

        switch (status.toUpperCase()) {
            case "PAID":
                isPaid = true;
                break;
            case "UNPAID":
                isPaid = false;
                break;
            default:
                isPaid = null;
                break;
        }

        List<OverdueRecordDTO> overdueRecordList = borrowRecordService.getAllOverdueRecords(isPaid);
        model.addAttribute("overdueRecords", overdueRecordList);
        model.addAttribute("currentStatus", status);

        return "overdue-records";
    }


    @PostMapping("/payPenalty")
    public String payPenalty(@RequestParam("penaltyId") Long penaltyId,
                             RedirectAttributes redirectAttributes) {
        try {
            penaltyService.payPenalty(penaltyId);
            redirectAttributes.addFlashAttribute("message", "Thanh toán thành công ✅");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Lỗi hệ thống, vui lòng thử lại.");
        }
        return "redirect:/overdue-records";
    }

    @GetMapping("/borrow-records")
    public String viewAllBorrowRecords(
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd")  LocalDate fromDate,
            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate toDate,
            @RequestParam(required = false, defaultValue = "ALL") String status,
            Model model
    ) {
        List<BorrowRecordDTO> borrowRecords = borrowRecordService.getBorrowRecords(fromDate, toDate, status);
        model.addAttribute("borrowRecords", borrowRecords);
        model.addAttribute("fromDate", fromDate);
        model.addAttribute("toDate", toDate);
        model.addAttribute("status", status);
        return "borrow-records";
    }

    @PostMapping("/{recordId}/return")
    public String confirmReturn(@PathVariable Long recordId, RedirectAttributes redirectAttributes) {
        try {
            boolean isOverdue = borrowRecordService.isOverdue(recordId);

            if (isOverdue) {
                redirectAttributes.addFlashAttribute("error",
                        "Phiếu mượn này đã quá hạn. Hãy thanh toán phạt trước khi trả sách!");
                return "redirect:/overdue-records";
            }

            borrowRecordService.returnBook(recordId);
            redirectAttributes.addFlashAttribute("message", "✅ Trả sách thành công!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Lỗi hệ thống, vui lòng thử lại.");
        }
        return "redirect:/borrow-records";
    }

//    @PostMapping("/{id}/lost")
//    public String reportLostBook(@PathVariable Long id, RedirectAttributes redirectAttributes) {
//        BorrowRecord record = borrowRecordService.findById(id);
//        double bookPrice = record.getBookItem().getBook().getPrice();
//        double totalFine = bookPrice * penaltyPolicyRepo.findActivePolicy().getLostBookFineRate();
//
//        penaltyService.createOrUpdatePenalty(record, fineSnapshot, totalFine);
//        redirectAttributes.addFlashAttribute("message", "Đã tạo/cập nhật phiếu phạt mất sách ✅");
//
//        return "redirect:/borrow-records";
//    }

}
