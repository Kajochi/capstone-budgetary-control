import {Entry} from "../model/Entry.ts";
import EntryCard from "../entryCard/EntryCard.tsx";
import {useStore} from "../hooks/useStore.ts";
import {useEffect} from "react";



export default function EntriesList() {
    const entries: Entry[] = useStore((state) => state.entries)
    useEffect(() => {
        useStore.getState().getEntries()
    }, [])
    return (
        <>
            {
                entries.map((entry) => <EntryCard entry={entry} key={entry.id} />)
            }

        </>

    )
}
