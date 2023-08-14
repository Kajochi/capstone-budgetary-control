import {Entry} from "../model/Entry.ts";
import EntryCard from "../entryCard/EntryCard.tsx";
import {useStore} from "../hooks/useStore.ts";
import {useEffect, useState} from "react";
import styled from "@emotion/styled";
import MonthlyBalanceAmountsCard from "../monthlyBalanceAmountsCard/MonthlyBalanceAmountsCard.tsx";


type Props = {
    monthYear: string;
}

export default function EntriesList(props: Props) {
    const [monthlyEntries, setMonthlyEntries] = useState<Entry []>()

    const monthlyBalances = useStore((state) => state.monthlyBalances)

    useEffect(() => {
        useStore.getState().getMonthlyBalances()

        checkIfEntriesExist()
    }, [])

    useEffect(() => {
        checkIfEntriesExist()
    }, [props.monthYear])

    function checkIfEntriesExist() {
        if (
            monthlyBalances[props.monthYear].monthlyEntries !== undefined
        ) {
            setMonthlyEntries(monthlyBalances[props.monthYear].monthlyEntries);

        } else {
            setMonthlyEntries(undefined);
        }
    }

    return (
        <>

            {
                monthlyEntries ? (
                    <>
                        <MonthlyBalanceAmountsCard monthlyBalance={monthlyBalances[props.monthYear]}/>
                        {monthlyEntries?.map((entry: Entry) => {
                            return <EntryCard key={entry.id} entry={entry} monthYear={props.monthYear}/>
                        })}
                    </>
                ) : (
                    <StyledP>There are no entries for this month</StyledP>
                )
            }

        </>

    )
}
const StyledP = styled.p`
  font-family: "Roboto Light", sans-serif;
  display: flex;
  justify-content: center;
`;