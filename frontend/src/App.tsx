import styled from "@emotion/styled";
import {Route, Routes, useSearchParams} from "react-router-dom";
import EntryAddUpdate from "./entryAddUpdate/EntryAddUpdate.tsx";
import Home from "./home/Home.tsx";
import LongMenu from "./navBar/LongMenu.tsx";
import SavingsIcon from '@mui/icons-material/Savings';
import MonthlyBalance from "./monthlyBalance/MonthlyBalance.tsx";
import FinanceReport from "./financeReport/FinanceReport.tsx";

export default function App() {

    const [selectedMonthYear] = useSearchParams()
    const monthYear = selectedMonthYear.get("monthYear")
    return (
        <>
            <StyledDiv>
                <HeaderChildDiv1 >
                    <SavingsIcon/>
                </HeaderChildDiv1>
                <HeaderChildDiv2 >
                <StyledH1 >Budgetary Control</StyledH1>
                </HeaderChildDiv2>
                <HeaderChildDiv3 >
                    <LongMenu/>
                </HeaderChildDiv3>
            </StyledDiv>
            <Routes>
                <Route path={"/"} element={<Home/>}/>
                <Route path={"/add-entry"} element={<EntryAddUpdate/>}/>
                <Route path={"/monthlyBalance"} element={<MonthlyBalance monthYear={monthYear}/>}/>
                <Route path={"/financeReport"} element={<FinanceReport/>}/>
            </Routes>

        </>

    )
}

const StyledH1 = styled.h2`
  font-family: "Roboto Light", sans-serif;
  
`;

const StyledDiv = styled.div`
  display: flex;
  background-color: #49e0e3;
  align-items: center;
  width: 100%;
  margin-top: 15px;
  border-radius: 7px;
`;
const HeaderChildDiv1 = styled.div`
  justify-content: flex-start;
  margin-left: 10px;
 
`;
const HeaderChildDiv2 = styled.div`
    justify-content: center;
    width: 100%;
  margin-left: 5px;
`;

const HeaderChildDiv3 = styled.div`
    justify-content: flex-end;
  margin-right: 10px;
`;

