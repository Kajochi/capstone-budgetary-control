import {Period} from "./Period.ts";

export type FinanceReport = {

    period: Period,
    totalIncome: string,
    totalExpenses: string,
    fixCosts: string,
    variableCosts: string,
    balance: string,

}