package com.groupTuAnh.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.groupTuAnh.dto.MemberShipResponse;
import com.groupTuAnh.dto.PageResponse;
import com.groupTuAnh.dto.ReaderSearchRequest;
import com.groupTuAnh.exception.ResourceNotFoundException;
import com.groupTuAnh.service.MemberShipService;
import com.groupTuAnh.service.ReaderService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/memberships")
@Slf4j
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class MembershipController {

    private final MemberShipService memberShipService;
    private final ReaderService readerService;

    @GetMapping
    public List<MemberShipResponse> getAllMemberships() {
        List<MemberShipResponse> memberShips = memberShipService.getAll();
        log.info("memberships size: {}", memberShips.size());
        return memberShips;
    }

    @PostMapping("/update")
    public ResponseEntity<?> updateMembership(@RequestParam long memberShipId, @RequestParam String studentCode) {
        try {
            memberShipService.updateMembership(studentCode, memberShipId);
            return ResponseEntity.ok().body(Map.of("message", "Cập nhật thành công"));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/search")
    public PageResponse<?> searchReaders(@RequestBody ReaderSearchRequest request) {
        return readerService.getAllByMember(request);
    }

}
