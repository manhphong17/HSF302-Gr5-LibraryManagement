package com.groupTuAnh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/memberships")
public class MembershipController {

    @GetMapping
    public String membership() {
        return "membership";
    }
}
