package com.groupTuAnh.controller;



import com.groupTuAnh.service.BorrowRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import com.groupTuAnh.dto.OverdueRecordDTO;
import java.util.List;

@Controller
@RequiredArgsConstructor
class BorrowRecordController {
    private final BorrowRecordService borrowRecordService;

    @GetMapping("/overdue-records")
    public String getAllOverdueRecords(Model model) {
        List<OverdueRecordDTO> overdueRecordList = borrowRecordService.getAllOverdueRecords();
    model.addAttribute("overdueRecords",overdueRecordList );
    return "overdue-records";
    }

}
