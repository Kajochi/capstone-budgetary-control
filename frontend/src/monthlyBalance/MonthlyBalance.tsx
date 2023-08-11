import styled from "@emotion/styled";
import {FormControl, InputLabel, MenuItem, Select} from "@mui/material";
import {useEffect, useState} from "react";
import EntriesList from "../entriesList/EntriesList.tsx";


export default function MonthlyBalance() {
    const [year, setYear] = useState<string>("2021")
    const [month, setMonth] = useState<string>("JANUARY")
    const [monthYear, setMonthYear] = useState<string>("")

    useEffect(() => {
        setMonthYear(month + "-" + year);
    }, [month, year]);


    return (
        <div>
            <StyledH2>Monthly Balance</StyledH2>
            <StyledDropDownContainer>
                <div>
            <FormControl >
                <InputLabel id="year">Choose a Year</InputLabel>
                <Select labelId="year"
                        value={year}
                        onChange={(e) => setYear(e.target.value as string)}
                >
                    <MenuItem value="2021">2021</MenuItem>
                    <MenuItem value="2022">2022</MenuItem>
                    <MenuItem value="2023">2023</MenuItem>
                    <MenuItem value="2024">2024</MenuItem>
                </Select>
            </FormControl>
                </div>
                <div>
            <FormControl >
                <InputLabel id="month">Choose a month</InputLabel>
                <Select labelId="month"
                        value={month}
                        onChange={(e) => setMonth(e.target.value as string)}
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
                </div>
                </StyledDropDownContainer>
            <EntriesList monthYear={monthYear}/>
        </div>
    )
}

const StyledH2 = styled.h2`
  font-family: "Roboto Light", sans-serif;
  display: flex;
  justify-content: center;
    `;

const StyledDropDownContainer = styled.div`
    display: flex;
    justify-content: center;
    gap: 20px;
    margin: 20px;
    `;