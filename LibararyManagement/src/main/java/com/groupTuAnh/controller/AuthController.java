package com.groupTuAnh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.groupTuAnh.enums.UserRole;
import com.groupTuAnh.model.Account;
import com.groupTuAnh.service.AuthService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @GetMapping("/login")
    public String login(){
        return "login";
    }


    @PostMapping("/login")
    public String login(HttpSession session,
                        @RequestParam String username,
                        @RequestParam String password,
                        Model model) {
        try {
            Account user = authService.authenticate(username, password);
            session.setAttribute("userId", user.getAccountId());

            if(user.getRole().equals(UserRole.READER)){
                return "redirect:/home";

            }
            else{
                return "redirect:/dashboard";
            }

        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout() {
        // TODO: Implement logout logic
        return "redirect:/login?logout=true";
    }

}
