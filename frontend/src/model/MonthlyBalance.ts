import {Entry} from "./Entry.ts";

export type MonthlyBalance = {
    yearMonth: string;
    totalIncome: string;
    totalExpenses: string;
    fixedCosts: string;
    variableCosts: string;
    oneTimeCosts: string;
    balance: string;
    monthlyEntries: Entry [];
}

export type MonthlyBalanceAmounts = {
    totalIncome: string;
    totalExpense: string;
    fixedCosts: string;
    variableCosts: string;
    oneTimeCosts: string;
    balance: string;
}