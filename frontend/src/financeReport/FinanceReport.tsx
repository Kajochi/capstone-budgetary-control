import styled from "@emotion/styled";
import {FormControl, InputLabel, MenuItem, Select} from "@mui/material";

import {useEffect, useState} from "react";

import {useStore} from "../hooks/useStore.ts";
import FinanceReportCard from "../financeReportCard/FinanceReportCard.tsx";
import {Interval} from "../model/Interval.ts";
import CalculateIcon from '@mui/icons-material/Calculate';



export default function FinanceReport() {

    const [period, setPeriod] = useState<Interval>('MONTHLY');

   const financeReports = useStore((state) => state.financeReports)

    const getFinanceReports = useStore((state) => state.getFinanceReports)

    useEffect(() => {
        getFinanceReports()
    }, [])




    return (
        <div>
            <HeadingDiv>
            <StyledH2>Finance Report</StyledH2>
                <CalculateIcon fontSize="large" />
            </HeadingDiv>
            <StyledFormControl >
                <InputLabel id="period">Period</InputLabel>
                <Select labelId="period" label={"Period"}
                        value={period}
                        onChange={(e) => setPeriod(e.target.value as Interval)}
                >
                    <MenuItem value="MONTHLY">Monthly</MenuItem>
                    <MenuItem value="QUARTERLY">Quarterly</MenuItem>
                    <MenuItem value="HALF_YEARLY">Half Yearly</MenuItem>
                    <MenuItem value="YEARLY">Yearly</MenuItem>
                </Select>
            </StyledFormControl>
            <FinanceReportCard period={period} financeReports={financeReports}/>
        </div>
    )
}

const StyledH2 = styled.h2`
  font-family: "Roboto Light", sans-serif;
  display: flex;
  justify-content: center;
    `;

const StyledFormControl = styled(FormControl)`
    display: flex;
    justify-content: center;
  margin: 25px;
    `;
const HeadingDiv = styled.div`
     display: flex;
    justify-content: center;
    align-items: center;
    `;