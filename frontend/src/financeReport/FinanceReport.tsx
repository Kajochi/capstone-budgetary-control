import styled from "@emotion/styled";
import {FormControl, InputLabel, MenuItem, Select} from "@mui/material";

import {useEffect, useState} from "react";
import {Period} from "../model/Period.ts";
import {useStore} from "../hooks/useStore.ts";
import FinanceReportCard from "../financeReportCard/FinanceReportCard.tsx";



export default function FinanceReport() {

    const [period, setPeriod] = useState<Period>('MONTH');

   const financeReports = useStore((state) => state.financeReports)

    useEffect(() => {
        useStore.getState().getFinanceReports()

    }, [])





    return (
        <div>
            <StyledH2>Finance Report</StyledH2>
            <FormControl >
                <InputLabel id="period">Period</InputLabel>
                <Select labelId="intervall" autoWidth={true}
                        value={period}
                        onChange={(e) => setPeriod(e.target.value as Period)}
                >
                    <MenuItem value="MONTH">Month</MenuItem>
                    <MenuItem value="QUARTER">Quarter</MenuItem>
                    <MenuItem value="HALF_YEAR">Half Year</MenuItem>
                    <MenuItem value="YEAR">Year</MenuItem>
                </Select>
            </FormControl>
           <FinanceReportCard period={period} financeReports={financeReports}/>
        </div>
    )
}

const StyledH2 = styled.h2`
  font-family: "Roboto Light", sans-serif;
  display: flex;
  justify-content: center;
    `;