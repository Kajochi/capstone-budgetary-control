import styled from "@emotion/styled";
import {FormControl, InputLabel, MenuItem, Select} from "@mui/material";
import {useEffect, useState} from "react";
import EntriesList from "../entriesList/EntriesList.tsx";
import {useStore} from "../hooks/useStore.ts";
import AccountBalanceIcon from '@mui/icons-material/AccountBalance';

type Props = {
    monthYear: string;
}


export default function MonthlyBalance(props: Props) {

    function isPropsNull() {
        if (props.monthYear === "null") {
            return null
        }else {
            return props.monthYear
        }
    }

    const [year, setYear] = useState<string>(isPropsNull()? props.monthYear.split("-")[1] : "2023")
    const [month, setMonth] = useState<string>(isPropsNull()? props.monthYear.split("-")[0] : "JANUARY")
    const [monthYear, setMonthYear] = useState<string>("JANUARY-2021")

    useStore.getState().getEntries()

    useEffect(() => {
        setMonthYear(month + "-" + year)
    }, [year, month])

    return (
        <div>
            <HeadingDiv>
            <StyledH2>Monthly Balance</StyledH2>
            <AccountBalanceIcon/>
            </HeadingDiv>
            <ExplonationText>

                <p>Here you can see your monthly balance.
                Choose a month and a year to see the balance of this chosen month.</p>
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
    `;

const StyledDropDownContainer = styled.div`
    display: flex;
    flex-direction: column;
    gap: 20px;
    margin: 25px;
    `;

const ExplonationText = styled.div`
  padding: 4px;
  border-radius: 7px;
  background-color: #33ec97;
  font-family: "Roboto Light", sans-serif;
  display: flex;
  flex-direction: column;
  justify-content: center;
  margin: 16px;
  font-size: 16px;
  font-weight: 500;
  line-height: 1.6;
  letter-spacing: 0.0075em;
  text-align: center;
 `;

const HeadingDiv = styled.div`
    display: flex;
    justify-content: center;
    align-items: center;
    `;
