package de.neuefische.capstone.backend.monthlybalance;

import de.neuefische.capstone.backend.model.MonthlyBalance;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/monthlybalance")
public class MonthlyBalanceController {

    private final MonthlyBalanceService monthlyBalanceService;

    MonthlyBalanceController (MonthlyBalanceService monthlyBalanceService) {
        this.monthlyBalanceService = monthlyBalanceService;
    }

    @GetMapping
    Map<String, MonthlyBalance> getMonthlyBalanceList() {
        return monthlyBalanceService.getMonthlyBalanceList();
    }
}
