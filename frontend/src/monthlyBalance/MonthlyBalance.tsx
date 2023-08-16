import styled from "@emotion/styled";
import {FormControl, InputLabel, MenuItem, Select} from "@mui/material";
import {useEffect, useState} from "react";
import EntriesList from "../entriesList/EntriesList.tsx";
import {useStore} from "../hooks/useStore.ts";


type Props = {
    monthYear: string | null;
}


export default function MonthlyBalance(props: Props) {
    const [year, setYear] = useState<string>("2021")
    const [month, setMonth] = useState<string>("JANUARY")
    const [monthYear, setMonthYear] = useState<string>("JANUARY-2021")

    useStore.getState().getEntries()

    useEffect(() => {
        setMonthYear(month + "-" + year)
    }, [year, month])

    useEffect(() => {
        if (props.monthYear === null) {
            setMonth("JANUARY")
            setYear("2021")
        }else {
            const month = props.monthYear.split("-")[0]
            const year = props.monthYear.split("-")[1]
            setMonth(month)
            setYear(year)
        }
    }, [])

    return (
        <div>
            <StyledH2>Monthly Balance</StyledH2>
            <StyledDropDownContainer>
                <StyledDiv>
            <FormControl >
                <InputLabel id="year">Choose a Year</InputLabel>
                <Select labelId="year"
                        value={ year }

                        onChange={(e) => setYear(e.target.value)}
                >
                    <MenuItem value="2021">2021</MenuItem>
                    <MenuItem value="2022">2022</MenuItem>
                    <MenuItem value="2023">2023</MenuItem>
                    <MenuItem value="2024">2024</MenuItem>
                </Select>
            </FormControl>

            <FormControl >
                <InputLabel id="month">Choose a month</InputLabel>
                <Select labelId="month"
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
                </StyledDiv>
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

const StyledDiv = styled.div`
  display: flex;
  flex-direction: column;
  margin: 16px;
`;