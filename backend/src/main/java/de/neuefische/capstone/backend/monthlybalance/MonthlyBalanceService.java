package de.neuefische.capstone.backend.monthlybalance;

import de.neuefische.capstone.backend.model.MonthlyBalance;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Data
@AllArgsConstructor
public class MonthlyBalanceService {

        private final MonthlySort monthlySort;

        public Map<String, MonthlyBalance> getMonthlyBalanceList() {
                return monthlySort.generateMonthlyBalanceList();
        }




}
