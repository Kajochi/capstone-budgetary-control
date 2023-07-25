import {Entry} from "../model/entry.ts";
import EntryCard from "../entryCard/EntryCard.tsx";

type Props = {
    entries: Entry[];
}

export default function EntriesList(props: Props) {

    return (
        <>
            {
                props.entries.map((entry) => <EntryCard entry={entry} key={entry.id} />)
            }

        </>

    )
}
