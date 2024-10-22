package com.hits.backend2.backend.data.implementations;

import com.hits.backend2.backend.data.clients.rates.RatesClient;
import com.hits.backend2.backend.domain.services.SalaryCalculatorService;
import com.hits.backend2.crutch.model.EmployeeInfoResponse;
import com.hits.backend2.integrations.model.EmployeeDto;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.metrics.stats.Rate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Service
public class SalaryCalculatorServiceImpl implements SalaryCalculatorService {
    private static final Double BASE_SALARY = 10.0;

    private final RatesClient ratesClient;

    @Override
    public Double calculateSalary(EmployeeInfoResponse employeeInfo, Double workingHoursPerDay, String currency) {
        String employeePosition = employeeInfo.getJobTitle();
        double salaryPerHour = BASE_SALARY * getSalaryBet(employeePosition);
        BigDecimal rate = ratesClient.getRates().getRates().get(currency);

        if (rate == null) {
            throw new IllegalArgumentException("Currency not found");
        }

        salaryPerHour *= rate.doubleValue();
        return salaryPerHour * workingHoursPerDay * 20;
    }

    private Double getSalaryBet(String employeePosition) {
        return switch (employeePosition) {
            case "MANAGER" -> 100.0;
            case "EMPLOYEE" -> 50.0;
            case "TECH" -> 75.0;
            default -> 0.0;
        };
    }
}
