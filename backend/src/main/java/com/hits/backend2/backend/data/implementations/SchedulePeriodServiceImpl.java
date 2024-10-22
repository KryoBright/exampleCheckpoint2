package com.hits.backend2.backend.data.implementations;

import com.hits.backend2.backend.data.clients.schedulePeriod.SchedulePeriodClient;
import com.hits.backend2.backend.domain.services.SchedulePeriodService;
import com.hits.backend2.integrations.model.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalTime;

@AllArgsConstructor
@Service
public class SchedulePeriodServiceImpl implements SchedulePeriodService {
    private SchedulePeriodClient schedulePeriodClient;

    @Override
    public Double getWorkingHoursPerDay(String employeeId) {
        SchedulePeriodsDto periods = schedulePeriodClient.findPeriods(
                new SchedulePeriodRequestDto()
                        .filter(new SchedulePeriodFilterDto().executorId(employeeId))
                        .sort(new SchedulePeriodSortDto().size(Integer.MAX_VALUE))
        );

        double totalHours = 0.0;

        for (SchedulePeriodDto period : periods.getContent()) {
            LocalTime beginTime = LocalTime.parse(period.getScheduleSlot().getBeginTime());
            LocalTime endTime = LocalTime.parse(period.getScheduleSlot().getEndTime());

            Duration duration = Duration.between(beginTime, endTime);
            totalHours += duration.toHours();
        }

        return totalHours;
    }
}
