package com.hits.backend2.backend.data.implementations;

import com.hits.backend2.backend.domain.services.EmployeeService;
import com.hits.backend2.backend.domain.services.ReportsService;
import com.hits.backend2.backend.domain.services.SalaryCalculatorService;
import com.hits.backend2.crutch.model.EmployeeInfoResponse;
import com.hits.backend2.crutch.model.ReportListResponseInner;
import com.hits.backend2.crutch.model.SpecificReportResponse;
import com.hits.backend2.crutch.model.SpecificReportResponseEmployeeHoursInner;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

import static org.apache.kafka.common.serialization.Serdes.UUID;

@Service
@RequiredArgsConstructor
public class ReportsServiceImpl implements ReportsService {
    private static final String TOPIC_ID = "employee.service.reports";

    private final EmployeeService employeeService;
    private final SalaryCalculatorService salaryCalculatorService;
    private final KafkaTemplate<String, String> kafkaTemplate;

    private long lastMessageReceivedTimestamp;

    @Scheduled(cron = "0 0 0 * * MON")
    @Override
    public void createReport() {
        List<String> usersIds = employeeService.getEmployeeIds();

        List<SpecificReportResponseEmployeeHoursInner> reports = usersIds.stream()
                .map(userId -> {
                    EmployeeInfoResponse employeeInfo = employeeService.getEmployeeInfo(userId);
                    Double salary = salaryCalculatorService.calculateSalary(
                            employeeInfo,
                            employeeInfo.getTotalHoursWorked(),
                            "RUB"
                    );

                    return new SpecificReportResponseEmployeeHoursInner()
                            .employeeId(employeeInfo.getId())
                            .hoursWorked(employeeInfo.getTotalHoursWorked())
                            .salary(salary);
                })
                .toList();

        SpecificReportResponse specificReport =
                new SpecificReportResponse()
                        .reportId(UUID().toString())
                        .startDate(LocalDate.from(Instant.ofEpochMilli(lastMessageReceivedTimestamp)))
                        .endDate(LocalDate.now())
                        .employeeHours(reports);

        kafkaTemplate.send(TOPIC_ID, specificReport.toString());
    }

    @Override
    public List<ReportListResponseInner> getReports() {
        return List.of();
    }

    @Override
    public SpecificReportResponse getReport(String id, String currency) {
        return null;
    }

    @Override
    public void refreshReport(String id) {

    }

    @KafkaListener(topics = TOPIC_ID)
    public void listen(@Headers MessageHeaders headers) {
        lastMessageReceivedTimestamp = (long) headers.get(KafkaHeaders.RECEIVED_TIMESTAMP);
    }
}
