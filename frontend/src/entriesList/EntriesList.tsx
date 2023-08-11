import {Entry} from "../model/Entry.ts";
import EntryCard from "../entryCard/EntryCard.tsx";
import {useStore} from "../hooks/useStore.ts";
import {useEffect, useState} from "react";


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
            monthlyBalances[props.monthYear] &&
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
                monthlyEntries ?
                monthlyEntries?.map((entry: Entry) => {
                    return <EntryCard key={entry.id} entry={entry}/>
                }):
                <p>There are no entries for this month</p>

            }

        </>

    )
}
