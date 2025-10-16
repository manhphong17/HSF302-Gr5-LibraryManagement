package com.groupTuAnh.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.groupTuAnh.dto.MemberShipResponse;
import com.groupTuAnh.dto.PageResponse;
import com.groupTuAnh.dto.ReaderDetailsResponse;
import com.groupTuAnh.dto.ReaderSearchRequest;
import com.groupTuAnh.exception.ResourceNotFoundException;
import com.groupTuAnh.service.MemberShipService;
import com.groupTuAnh.service.ReaderService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/memberships")
@Slf4j
@RequiredArgsConstructor
public class MembershipController {
    private final MemberShipService memberShipService;
    private final ReaderService readerService;

    @GetMapping
    public String membership(
            Model model,
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Long memberShipId
    ) {
        List<MemberShipResponse> memberShips = memberShipService.getAll();
        model.addAttribute("memberShips", memberShips);

        ReaderSearchRequest req = ReaderSearchRequest.builder()
                .keyword(keyword)
                .memberShipId(memberShipId)
                .pageNo(1)
                .pageSize(1000)
                .sortBy("name:asc")
                .build();

        PageResponse<?> page = readerService.getAllByMember(req);
        @SuppressWarnings("unchecked")
        List<ReaderDetailsResponse> readers = (List<ReaderDetailsResponse>) page.getItems();
        model.addAttribute("readers", readers);
        model.addAttribute("keyword", keyword);
        model.addAttribute("memberShipId", memberShipId);
        return "membership";
    }

    @PostMapping("/update")
    public String updateMembershipForReader(@RequestParam long memberShipId, @RequestParam String studentCode, RedirectAttributes redirectAttributes) {
        try {
            memberShipService.updateMembership(studentCode, memberShipId);
            redirectAttributes.addFlashAttribute("success", "Cập nhật thành công");
        } catch (ResourceNotFoundException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
        }
        return "redirect:/memberships";
    }


}
