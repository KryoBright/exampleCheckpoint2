package com.hits.backend2.backend.domain.services;

import com.hits.backend2.crutch.model.EmployeeInfoResponse;

import java.util.List;

public interface EmployeeService {
    List<String> getEmployeeIds();

    EmployeeInfoResponse getEmployeeInfo(String id);
}
