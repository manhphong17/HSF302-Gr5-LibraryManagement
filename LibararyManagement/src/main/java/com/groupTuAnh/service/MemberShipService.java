package com.groupTuAnh.service;

import com.groupTuAnh.dto.MemberShipResponse;
import com.groupTuAnh.exception.ResourceNotFoundException;
import com.groupTuAnh.model.MembershipPackage;
import com.groupTuAnh.model.Reader;
import com.groupTuAnh.repository.MemberShipRepository;
import com.groupTuAnh.repository.ReaderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberShipService {

    private final MemberShipRepository memberShipRepository;
    private final ReaderRepository readerRepository;

    public List<MemberShipResponse> getAll() {
        List<MembershipPackage> membershipPackages = memberShipRepository.findAll();

        return membershipPackages.stream()
                .map(memberShip -> MemberShipResponse
                        .builder()
                        .price(memberShip.getPrice())
                        .id(memberShip.getPackageId())
                        .name(memberShip.getName())
                        .maxBookPerMonth(memberShip.getMaxBooksPerMonth())
                        .build())
                .toList();
    }

    public void updateMembership(String studentCode, long memberShipId) {
        Reader reader = getReaderByCode(studentCode.toUpperCase());

        MembershipPackage memberShip = getMembershipPackage(memberShipId);

        reader.setMembership(memberShip);
        memberShipRepository.save(memberShip);
        log.info("Member ship updated successfully");
    }

    private MembershipPackage getMembershipPackage(long id) {
        return memberShipRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Gói thành viên không tồn tại."));
    }

    private Reader getReaderByCode(String code) {
        return readerRepository.findByStudentCode(code).orElseThrow(() -> new ResourceNotFoundException("Độc giả không tồn tại trong hệ thống."));
    }
}
