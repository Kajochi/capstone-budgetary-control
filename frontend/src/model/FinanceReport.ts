import {Period} from "./Period.ts";

export type FinanceReport = {

    period: Period,
    totalIncome: string,
    totalExpense: string,
    fixCosts: string,
    variableCosts: string,
    balance: string,

}