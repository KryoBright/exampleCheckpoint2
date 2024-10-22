package com.hits.backend2.backend.data.clients.schedulePeriod;

import com.hits.backend2.integrations.model.SchedulePeriodRequestDto;
import com.hits.backend2.integrations.model.SchedulePeriodsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "schedule-period", url = "localhost:8080/api/schedulePeriod")
public interface SchedulePeriodClient {
    // контракт изменился относительно предыдущей кт
    @PostMapping("/advanced")
    SchedulePeriodsDto findPeriods(SchedulePeriodRequestDto requestDto);
}
