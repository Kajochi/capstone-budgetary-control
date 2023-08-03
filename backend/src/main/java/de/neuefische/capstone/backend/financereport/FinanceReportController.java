package de.neuefische.capstone.backend.financereport;

import de.neuefische.capstone.backend.model.FinanceReport;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/financeReports")
public class FinanceReportController {

    private final FinanceReportSerivce financeReportSerivce;

    FinanceReportController(FinanceReportSerivce financeReportSerivce) {
        this.financeReportSerivce = financeReportSerivce;
    }
    @GetMapping
    List<FinanceReport> getFinanceReports() {
        return financeReportSerivce.getFinanceReports();
    }
}
