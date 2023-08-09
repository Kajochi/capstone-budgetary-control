package de.neuefische.capstone.backend.financereport;

import de.neuefische.capstone.backend.model.FinanceReport;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
@AllArgsConstructor
public class FinanceReportService {

    private final FinanceReportCalculate financeReportCalculate;

    public List<FinanceReport> getFinanceReports() {
        return financeReportCalculate.calculateFinanceReports();
    }
}
