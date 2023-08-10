package de.neuefische.capstone.backend.monthlybalance;

import de.neuefische.capstone.backend.model.Category;
import de.neuefische.capstone.backend.model.CostType;
import de.neuefische.capstone.backend.model.Entry;
import de.neuefische.capstone.backend.model.Interval;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import java.util.List;
@Component
public class MonthlyBalanceCalculate {

    private MonthlyBalanceCalculate() {
    }
    public static List<BigDecimal> calculateEntryAmounts(List<Entry> entriesSortedByStartDate) {


         BigDecimal totalIncome = BigDecimal.ZERO;
         BigDecimal totalExpenses;
         BigDecimal fixedCosts = BigDecimal.ZERO;
         BigDecimal variableCosts = BigDecimal.ZERO;
         BigDecimal oneTimeCosts = BigDecimal.ZERO;


        for (Entry entry : entriesSortedByStartDate) {

            if (entry.getCostType().equals(CostType.FIXED)) {
               if (entry.getCategory().equals(Category.INCOME)){
                   totalIncome = totalIncome.add(entry.getAmount());
               }else{
                    fixedCosts = fixedCosts.add(entry.getAmount());
               }

            }else {
                if (entry.getCategory().equals(Category.INCOME)){
                    totalIncome = totalIncome.add(entry.getAmount());
                }else{
                    variableCosts = variableCosts.add(entry.getAmount());
                }
            }

            if (entry.getInterval().equals(Interval.ONCE)){
                oneTimeCosts = oneTimeCosts.add(entry.getAmount());
            }
        }


        totalExpenses = fixedCosts.add(variableCosts);
        BigDecimal balance = totalIncome.subtract(totalExpenses);

        return List.of(totalIncome, totalExpenses, fixedCosts, variableCosts, oneTimeCosts, balance);
    }
}
