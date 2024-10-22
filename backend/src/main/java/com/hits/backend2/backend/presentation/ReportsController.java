package com.hits.backend2.backend.presentation;

import com.hits.backend2.crutch.api.ReportsApi;
import com.hits.backend2.crutch.api.ReportsApiDelegate;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReportsController implements ReportsApi {
    private final ReportsApiDelegate delegate;

    @Override
    public ReportsApiDelegate getDelegate() {
        return delegate;
    }
}
