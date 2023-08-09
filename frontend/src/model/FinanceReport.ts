
import {Interval} from "./Interval.ts";

export type FinanceReport = {

    period: Interval,
    totalIncome: string,
    totalExpenses: string,
    fixCosts: string,
    variableCosts: string,
    balance: string,

}
