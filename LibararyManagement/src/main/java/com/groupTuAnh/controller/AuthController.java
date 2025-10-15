package com.groupTuAnh.controller;

import com.groupTuAnh.model.User;
import com.groupTuAnh.service.AuthService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public String login(HttpSession session,
                        @RequestParam String username,
                        @RequestParam String password,
                        Model model) {
        try {
            User user = authService.authenticate(username, password);
            session.setAttribute("userId", user.getUserId());
            return "redirect:/home";

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

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("title", "Dashboard - Thư viện số HSF302");
        return "dashboard";
    }
}
