import {Entry} from "../model/Entry.ts";
import EntryCard from "../entryCard/EntryCard.tsx";
import {useStore} from "../hooks/useStore.ts";
import {useEffect, useState} from "react";
import {MonthlyBalance} from "../model/MonthlyBalance.ts";

type Props = {
    monthYear: string;
}

export default function EntriesList(props: Props) {
    const [monthlyEntries, setMonthlyEntries] = useState<MonthlyBalance>()

    const monthlyBalances = useStore((state) => state.monthlyBalances)

    useEffect(() => {
        useStore.getState().getMonthlyBalances()
        setMonthlyEntries(monthlyBalances[props.monthYear])
    }, [])

    useEffect(() => {
        useStore.getState().getMonthlyBalances()
        setMonthlyEntries(monthlyBalances[props.monthYear])
    }, [props.monthYear])

    if (!monthlyEntries) {
        return <div>loading...</div>
    }
    return (
        <>

            {
                monthlyEntries?.entries.map((entry: Entry) => {
                    return <EntryCard key={entry.id} entry={entry}/>
                })
            }

        </>

    )
}
