import {Entry} from "../model/Entry.ts";
import EntryCard from "../entryCard/EntryCard.tsx";
import {useStore} from "../hooks/useStore.ts";
import {useEffect} from "react";
import styled from "@emotion/styled";
import MonthlyBalanceAmountsCard from "../monthlyBalanceAmountsCard/MonthlyBalanceAmountsCard.tsx";


type Props = {
    monthYear: string;
}

export default function EntriesList(props: Props) {


    const monthlyBalances = useStore((state) => state.monthlyBalances)
    const getMonthlyBalances = useStore((state) => state.getMonthlyBalances)


    useEffect(() => {

        getMonthlyBalances()
    }, [getMonthlyBalances])


    return (
        <>

            {
                monthlyBalances?.[props.monthYear]?.monthlyEntries ? (
                    <>
                        <MonthlyBalanceAmountsCard monthlyBalance={monthlyBalances[props.monthYear]}/>
                        {monthlyBalances?.[props.monthYear]?.monthlyEntries.map((entry: Entry) => {
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