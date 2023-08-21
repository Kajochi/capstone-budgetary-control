import styled from "@emotion/styled";
import {FormControl, InputLabel, MenuItem, Select} from "@mui/material";
import {useEffect, useState} from "react";
import EntriesList from "../entriesList/EntriesList.tsx";
import {useStore} from "../hooks/useStore.ts";
import AccountBalanceIcon from '@mui/icons-material/AccountBalance';


type Props = {
    monthYear: string | null;
}


export default function MonthlyBalance(props: Props) {

    const selectedMonthYear = useStore((state) => state.selectedMonthYear)

    const initialYear = selectedMonthYear && selectedMonthYear !== "null" ? selectedMonthYear.split("-")[1] : "2023";
    const initialMonth = selectedMonthYear && selectedMonthYear !== "null" ? selectedMonthYear.split("-")[0] : "JANUARY";

    const [year, setYear] = useState<string>(initialYear)
    const [month, setMonth] = useState<string>(initialMonth)

    const monthYear = `${month}-${year}`
    const getEntries = useStore((state) => state.getEntries)
    const setSelectedMonthYear = useStore((state) => state.setSelectedMonthYear)

    setSelectedMonthYear(monthYear)

    useEffect(() => {
        getEntries()
    }, [])

    return (
        <div>
            <HeadingDiv>
            <StyledH2>Monthly Balance</StyledH2>
            <AccountBalanceIcon fontSize="large"/>
            </HeadingDiv>
            <ExplonationText>

                <p>Here you can see your monthly balance.
                    Select a month and year to view the balance and all transactions for that selected month. </p>
            </ExplonationText>
            <StyledDropDownContainer>

            <FormControl >
                <InputLabel id="year">Choose a year</InputLabel>
                <Select label="Choose a Year" labelId={"year"}
                        value={ year }
                        onChange={(e) => setYear(e.target.value)}
                >

                    <MenuItem value="2023">2023</MenuItem>
                    <MenuItem value="2024">2024</MenuItem>
                </Select>
            </FormControl>

            <FormControl >
                <InputLabel id="month">Choose a month</InputLabel>
                <Select label="Choose a month" labelId={"month"}
                        value={ month }
                        onChange={(e) => setMonth(e.target.value)}
                >
                    <MenuItem value="JANUARY">January</MenuItem>
                    <MenuItem value="FEBRUARY">February</MenuItem>
                    <MenuItem value="MARCH">March</MenuItem>
                    <MenuItem value="APRIL">April</MenuItem>
                    <MenuItem value="MAY">May</MenuItem>
                    <MenuItem value="JUNE">June</MenuItem>
                    <MenuItem value="JULY">July</MenuItem>
                    <MenuItem value="AUGUST">August</MenuItem>
                    <MenuItem value="SEPTEMBER">September</MenuItem>
                    <MenuItem value="OCTOBER">October</MenuItem>
                    <MenuItem value="NOVEMBER">November</MenuItem>
                    <MenuItem value="DECEMBER">December</MenuItem>

                </Select>
            </FormControl>
                </StyledDropDownContainer>
            <EntriesList monthYear={monthYear}/>
        </div>
    )
}

const StyledH2 = styled.h2`
  font-family: "Roboto Light", sans-serif;
  display: flex;
  justify-content: center;
  margin-right: 4px;
  margin-top: 25px;
    `;

const StyledDropDownContainer = styled.div`
    display: flex;
    flex-direction: column;
    justify-content: center;
    gap: 20px;
    margin: 25px;
  margin-top: 40px;
  
    `;

const ExplonationText = styled.div`
  padding: 4px;
  border-radius: 7px;
  background-color: #edf0fc;
  font-family: "Roboto Light", sans-serif;
  display: flex;
  flex-direction: column;
  justify-content: center;
  margin-right: 20px;
  margin-left: 20px;
  margin-bottom: 25px;
  font-size: 16px;
  font-weight: 500;
  line-height: 1.6;
  letter-spacing: 0.0075em;
  text-align: center;
  box-shadow: 10px 10px 5px -4px silver;  
 `;

const HeadingDiv = styled.div`
    display: flex;
    justify-content: center;
    align-items: center;
    `;
