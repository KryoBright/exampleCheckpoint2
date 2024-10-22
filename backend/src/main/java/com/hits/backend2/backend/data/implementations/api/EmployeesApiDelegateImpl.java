package com.hits.backend2.backend.data.implementations.api;

import com.hits.backend2.backend.domain.services.EmployeeService;
import com.hits.backend2.crutch.api.EmployeesApiDelegate;
import com.hits.backend2.crutch.model.EmployeeInfoResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class EmployeesApiDelegateImpl implements EmployeesApiDelegate {
    private final EmployeeService employeeService;


    @Override
    public ResponseEntity<List<String>> getEmployeeIds() {
        return ResponseEntity.ok(
                employeeService.getEmployeeIds()
        );
    }

    @Override
    public ResponseEntity<EmployeeInfoResponse> getEmployeeInfo(String id) {
        return ResponseEntity.ok(employeeService.getEmployeeInfo(id));
    }
}
