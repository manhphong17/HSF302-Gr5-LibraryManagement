package com.groupTuAnh.controller;

import com.groupTuAnh.dto.BorrowRequestDTO;
import com.groupTuAnh.dto.ReturnRequestDTO;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalModelAttributes {
    @ModelAttribute("borrowDTO")
    public BorrowRequestDTO borrowDTO() { return new BorrowRequestDTO(); }
    @ModelAttribute("returnDTO")
    public ReturnRequestDTO returnDTO() { return new ReturnRequestDTO(); }
}
