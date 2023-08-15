import {MonthlyBalance} from "../model/MonthlyBalance.ts";


type Props = {
    monthlyBalance: MonthlyBalance;
}
export default function MonthlyBalanceAmountsCard(props: Props) {



    return (
        <>
            {props.monthlyBalance? (<>
            <p>Totalincome:  {props.monthlyBalance.totalIncome}€</p>
            <p>Totalexpense:  {props.monthlyBalance.totalExpenses}€</p>
            <p>Fixcosts:  {props.monthlyBalance.fixedCosts}€</p>
            <p>Variablecosts:  {props.monthlyBalance.variableCosts}€</p>
        <p>OneTimeCosts: {props.monthlyBalance.oneTimeCosts}€</p>
        <p>Balance:  {props.monthlyBalance.balance}€</p></>
            ) : (
                <p>loading...</p>
            )}
        </>
    )
}