package com.groupTuAnh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "Thư viện số HSF302");
        model.addAttribute("welcomeMessage", "Chào mừng đến với hệ thống quản lý thư viện");
        return "home";
    }

    @GetMapping("/home")
    public String homePage(Model model) {
        model.addAttribute("title", "Thư viện số HSF302");
        model.addAttribute("welcomeMessage", "Chào mừng đến với hệ thống quản lý thư viện");
        return "home";
    }
}
