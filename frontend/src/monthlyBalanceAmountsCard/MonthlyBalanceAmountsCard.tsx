import {MonthlyBalance} from "../model/MonthlyBalance.ts";
import styled from "@emotion/styled";
import Divider from '@mui/material/Divider';


type Props = {
    monthlyBalance: MonthlyBalance;
}
export default function MonthlyBalanceAmountsCard(props: Props) {



    return (
        <>
            {props.monthlyBalance? (<StyledDivContainer>
                    <TotalIncomeP >Total income:</TotalIncomeP><TotalIncomeAmountP > {props.monthlyBalance.totalIncome}€</TotalIncomeAmountP>
                    <Divider1 variant="middle"   />
            <TotalExpenseP >Total expense:   </TotalExpenseP><TotalExpenseAmountP> {props.monthlyBalance.totalExpenses}€</TotalExpenseAmountP>
                    <Divider2 variant="middle"  />
            <FixCostsP>Fix costs: </FixCostsP><FixCostsAmountP > {props.monthlyBalance.fixedCosts}€</FixCostsAmountP>
                    <Divider3 variant="middle"  />
            <VariableCostsP>Variable costs: </VariableCostsP><VariableCostsAmountP> {props.monthlyBalance.variableCosts}€</VariableCostsAmountP>
                    <Divider4 variant="middle"  />
        <OneTimeCostsP >One time costs: </OneTimeCostsP><OneTimeCostsAmountP>{props.monthlyBalance.oneTimeCosts}€</OneTimeCostsAmountP>
                   <DividerDiv>
                    <Divider5 />
                    <Divider6  />
                     </DividerDiv>
        <BalanceP>Balance: </BalanceP><BalanceAmountP> {props.monthlyBalance.balance}€</BalanceAmountP>
            </StyledDivContainer>
            ) : (
                <p>loading...</p>
            )}
        </>
    )
}

const StyledDivContainer = styled.div`
  display: grid;
  border: 1px solid #c2bfbf;
  border-radius: 7px;
  padding: 10px;
  grid-template-columns: 3fr 1fr;
  grid-template-rows: repeat(10, auto);
  justify-content: space-around;
  margin-left: 30px;
  margin-right: 30px;
  box-shadow: 10px 10px 5px -4px silver;
  grid-template-areas:
    "totalIncome totalIncomeAmount"
     "divider1 divider1"
    "totalExpense totalExpenseAmount"
    "divider2 divider2"
    "fixCosts fixCostsAmount"
    "divider3 divider3"
    "variableCosts variableCostsAmount"
    "divider4 divider4"
    "oneTimeCosts oneTimeCostsAmount"
    "dividerDiv dividerDiv"
    "balance balanceAmount"

`;

const TotalIncomeP = styled.p`
    grid-area: totalIncome;
  font-family: "Roboto Light", sans-serif;
    `;

const TotalIncomeAmountP = styled.p`
    grid-area: totalIncomeAmount;
  font-family: "Roboto Light", sans-serif;
  display: flex;
  justify-content: flex-end;
    `;
const TotalExpenseP = styled.p`
    grid-area: totalExpense;
  font-family: "Roboto Light", sans-serif;
    `;
const TotalExpenseAmountP = styled.p`
    grid-area: totalExpenseAmount;
  font-family: "Roboto Light", sans-serif;
  display: flex;
  justify-content: flex-end;
    `;
const FixCostsP = styled.p`
    grid-area: fixCosts;
  font-family: "Roboto Light", sans-serif;
    `;
const FixCostsAmountP = styled.p`
    grid-area: fixCostsAmount;
  font-family: "Roboto Light", sans-serif;
  display: flex;
  justify-content: flex-end;
    `;
const VariableCostsP = styled.p`
    grid-area: variableCosts;
  font-family: "Roboto Light", sans-serif;
    `;
const VariableCostsAmountP = styled.p`
    grid-area: variableCostsAmount;
  font-family: "Roboto Light", sans-serif;
  display: flex;
  justify-content: flex-end;
    `;
const OneTimeCostsP = styled.p`
    grid-area: oneTimeCosts;
  font-family: "Roboto Light", sans-serif;
    `;
const OneTimeCostsAmountP = styled.p`
    grid-area: oneTimeCostsAmount;
  font-family: "Roboto Light", sans-serif;
  display: flex;
  justify-content: flex-end;
    `;
const BalanceP = styled.p`
    grid-area: balance;
  font-family: "Roboto Light", sans-serif;
  font-weight: bold;
    `;
const BalanceAmountP = styled.p`
    grid-area: balanceAmount;
  font-family: "Roboto Light", sans-serif;
  display: flex;
  justify-content: flex-end;
  font-weight: bold;
    `;
const Divider1 = styled(Divider)`
    grid-area: divider1;
  font-family: "Roboto Light", sans-serif;
  display: flex;
  justify-content: flex-end;
    `;
const Divider2 = styled(Divider)`
    grid-area: divider2;
  font-family: "Roboto Light", sans-serif;
  display: flex;
  justify-content: flex-end;
    `;
const Divider3 = styled(Divider)`
    grid-area: divider3;
  font-family: "Roboto Light", sans-serif;
  display: flex;
  justify-content: flex-end;
    `;
const Divider4 = styled(Divider)`
    grid-area: divider4;
  font-family: "Roboto Light", sans-serif;
  display: flex;
  justify-content: flex-end;
    `;
const Divider5 = styled(Divider)`
    grid-area: divider5;
  font-family: "Roboto Light", sans-serif;
  display: flex;
  justify-content: flex-end;
    `;
const Divider6 = styled(Divider)`
    grid-area: divider6;
  font-family: "Roboto Light", sans-serif;
  display: flex;
  justify-content: flex-end;
    `;

const DividerDiv = styled.div`
    grid-area: dividerDiv;
  
    `;