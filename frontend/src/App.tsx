import styled from "@emotion/styled";
import {Route, Routes} from "react-router-dom";
import EntryAddUpdate from "./entryAddUpdate/EntryAddUpdate.tsx";
import Home from "./home/Home.tsx";
import LongMenu from "./navBar/LongMenu.tsx";
import SavingsIcon from '@mui/icons-material/Savings';
import MonthlyBalance from "./monthlyBalance/MonthlyBalance.tsx";
import FinanceReport from "./financeReport/FinanceReport.tsx";

export default function App() {



    return (
        <>
            <StyledDiv>
                <HeaderChildDiv1 >
                    <SavingsIcon fontSize="large"/>
                </HeaderChildDiv1>
                <StyledH1 >Budgetary Control</StyledH1>
                <HeaderChildDiv3 >
                    <LongMenu/>
                </HeaderChildDiv3>
            </StyledDiv>
            <Routes>
                <Route path={"/"} element={<Home/>}/>
                <Route path={"/add-entry"} element={<EntryAddUpdate/>}/>
                <Route path={"/monthlyBalance"} element={<MonthlyBalance/>}/>
                <Route path={"/financeReport"} element={<FinanceReport/>}/>
            </Routes>

        </>

    )
}

const StyledH1 = styled.h2`
  font-family: "Roboto Light", sans-serif;
  color: #ffffff;
  justify-content: center;
  width: 100%;
  margin-left: 5px;
`;

const StyledDiv = styled.div`
  display: flex;
  background-color: #4d6bdd;
  align-items: center;
  width: 100%;
  margin-top: 15px;
  border-radius: 7px;
  box-shadow: 10px 10px 5px -4px silver;
`;
const HeaderChildDiv1 = styled.div`
  justify-content: flex-start;
  margin-left: 10px;
 
`;


const HeaderChildDiv3 = styled.div`
    justify-content: flex-end;
  margin-right: 10px;
  
`;

