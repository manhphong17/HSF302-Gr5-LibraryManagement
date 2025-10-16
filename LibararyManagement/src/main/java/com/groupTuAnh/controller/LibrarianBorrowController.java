package com.groupTuAnh.controller;

import com.groupTuAnh.dto.BorrowRequestDTO;
import com.groupTuAnh.dto.ReturnRequestDTO;
import com.groupTuAnh.model.BorrowRecord;
import com.groupTuAnh.service.BorrowService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
@RequestMapping("/librarian") // prefix riêng, KHÔNG đụng /dashboard
public class LibrarianBorrowController {

    private final BorrowService borrowService;

    @PostMapping("/borrow")
    public String borrow(@ModelAttribute BorrowRequestDTO dto, RedirectAttributes ra) {
        try {
            BorrowRecord r = borrowService.borrow(
                    dto.getStudentCode(), dto.getBookItemId(), dto.getLibrarianId(), dto.getDueDate()
            );
            ra.addFlashAttribute("borrowMsg",
                    "Tạo phiếu mượn #" + r.getRecordId() + " thành công. Hạn: " + r.getDueDate());
        } catch (RuntimeException ex) {
            ra.addFlashAttribute("borrowError", ex.getMessage());
            ra.addFlashAttribute("borrowDTO", dto);
        }
        return "redirect:/dashboard#borrowing";
    }

    @PostMapping("/return")
    public String returnBook(@ModelAttribute ReturnRequestDTO dto, RedirectAttributes ra) {
        try {
            BorrowRecord r = borrowService.returnBorrow(dto.getRecordId(), dto.getLibrarianId());
            ra.addFlashAttribute("returnMsg",
                    "Phiếu mượn #" + r.getRecordId() + " đã trả ngày " + r.getReturnDate());
        } catch (RuntimeException ex) {
            ra.addFlashAttribute("returnError", ex.getMessage());
            ra.addFlashAttribute("returnDTO", dto);
        }
        return "redirect:/dashboard#return";
    }
}
