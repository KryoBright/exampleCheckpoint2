package com.hits.backend2.backend.domain.services;

import com.hits.backend2.crutch.model.ReportListResponseInner;
import com.hits.backend2.crutch.model.SpecificReportResponse;

import java.util.List;

public interface ReportsService {
    void createReport();

    List<ReportListResponseInner> getReports();

    SpecificReportResponse getReport(String id, String currency);

    void refreshReport(String id);
}
