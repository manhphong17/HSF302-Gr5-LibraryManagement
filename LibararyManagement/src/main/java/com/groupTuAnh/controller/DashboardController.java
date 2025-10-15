package com.groupTuAnh.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/dashboard")
    public String dashboard(HttpSession session) {
        if (session.getAttribute("userId") == null) {
            return "login";
        } else {
            return "dashboard";
        }
    }
}
