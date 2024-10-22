package com.hits.backend2.backend.data.clients.employees;

import com.hits.backend2.integrations.model.EmployeeDto;
import com.hits.backend2.integrations.model.SchedulePeriodRequestDto;
import com.hits.backend2.integrations.model.SchedulePeriodsDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "employee", url = "localhost:8080/api/employee")
public interface EmployeeClient {
    @GetMapping
    List<EmployeeDto> getAll();

    @GetMapping("/{id}")
    EmployeeDto getById(@PathVariable String id);
}
