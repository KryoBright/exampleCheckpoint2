package com.hits.backend2.backend.data.implementations.api;

import com.hits.backend2.backend.domain.services.ReportsService;
import com.hits.backend2.crutch.api.ReportsApiDelegate;
import com.hits.backend2.crutch.model.ReportListResponseInner;
import com.hits.backend2.crutch.model.SpecificReportResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ReportsApiDelegateImpl implements ReportsApiDelegate {
    private final ReportsService reportsService;

    @Override
    public ResponseEntity<List<ReportListResponseInner>> getReportList() {
        return ResponseEntity.ok(reportsService.getReports());
    }

    @Override
    public ResponseEntity<SpecificReportResponse> getSpecificReport(String id, String currency) {
        return ResponseEntity.ok(reportsService.getReport(id, currency));
    }

    @Override
    public ResponseEntity<Void> refreshReport(String id) {
        reportsService.refreshReport(id);

        return ResponseEntity.ok().build();
    }
}
