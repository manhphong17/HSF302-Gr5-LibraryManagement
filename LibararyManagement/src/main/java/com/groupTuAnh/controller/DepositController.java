package com.groupTuAnh.controller;

import com.groupTuAnh.dto.DepositRequest;
import com.groupTuAnh.model.Transaction;
import com.groupTuAnh.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/librarian/deposit")
@RequiredArgsConstructor
public class DepositController {

    private final WalletService walletService;

    @GetMapping
    public String depositForm(Model model) {
        if (!model.containsAttribute("depositRequest")) {
            model.addAttribute("depositRequest", new DepositRequest(null, null, null, ""));
        }
        return "librarian/deposit-form";
    }

    @PostMapping
    public String doDeposit(@ModelAttribute DepositRequest depositRequest, RedirectAttributes redirectAttributes) {
        try {
            Transaction transaction = walletService.deposit(depositRequest);
            redirectAttributes.addFlashAttribute("msg", "Deposit success: +" + transaction.getAmount());
        } catch (RuntimeException ex) {
            redirectAttributes.addFlashAttribute("error", ex.getMessage());
            redirectAttributes.addFlashAttribute("depositRequest", depositRequest);
        }
        return "redirect:/librarian/deposit";
    }
}
