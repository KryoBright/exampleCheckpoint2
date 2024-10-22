package com.hits.backend2.backend.data.implementations;

import com.hits.backend2.backend.data.clients.employees.EmployeeClient;
import com.hits.backend2.backend.domain.mappers.EmployeeDtoMapper;
import com.hits.backend2.backend.domain.services.EmployeeService;
import com.hits.backend2.crutch.model.EmployeeInfoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.hits.backend2.integrations.model.EmployeeDto;

import java.util.List;

@RequiredArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeClient employeeClient;
    private final SchedulePeriodServiceImpl schedulePeriodService;

    @Override
    public List<String> getEmployeeIds() {
        return employeeClient.getAll().stream()
                .map(EmployeeDto::getId)
                .toList();
    }

    @Override
    public EmployeeInfoResponse getEmployeeInfo(String id) {
        EmployeeDto employeeDto = employeeClient.getById(id);
        EmployeeInfoResponse employeeFullInfoDto =
                EmployeeDtoMapper.INSTANCE.toEmployeeInfoResponse(employeeDto);
        double workingHoursPerDay = schedulePeriodService.getWorkingHoursPerDay(id);

        employeeFullInfoDto.setTotalHoursWorked(workingHoursPerDay);

        return employeeFullInfoDto;
    }
}
