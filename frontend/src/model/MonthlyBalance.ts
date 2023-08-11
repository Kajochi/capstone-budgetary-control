import {Entry} from "./Entry.ts";

export type MonthlyBalance = {
    yearMonth: string;
    totalIncome: string;
    totalExpense: string;
    fixedCosts: string;
    variableCosts: string;
    onetimeCosts: string;
    balance: string;
    monthlyEntries: Entry [];
}