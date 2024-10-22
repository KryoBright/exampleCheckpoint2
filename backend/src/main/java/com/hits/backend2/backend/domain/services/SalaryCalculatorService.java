package com.hits.backend2.backend.domain.services;

import com.hits.backend2.crutch.model.EmployeeInfoResponse;

public interface SalaryCalculatorService {
    Double calculateSalary(EmployeeInfoResponse employeeInfo, Double workingHoursPerDay, String currency);
}
