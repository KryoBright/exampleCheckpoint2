package com.hits.backend2.backend.presentation;

import com.hits.backend2.crutch.api.EmployeesApi;
import com.hits.backend2.crutch.api.EmployeesApiDelegate;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class EmployeeController implements EmployeesApi {
    private final EmployeesApiDelegate delegate;

    @Override
    public EmployeesApiDelegate getDelegate() {
        return delegate;
    }
}
