package com.groupTuAnh.scheduler;

import com.groupTuAnh.service.PenaltyService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class PenaltyUpdateScheduler {
private final PenaltyService penaltyService;

    @Scheduled(cron = "0 * * * * *", zone = "Asia/Ho_Chi_Minh")
    public void updateOverduePenalties(){
    penaltyService.updateOverduePenalties();
}
}
