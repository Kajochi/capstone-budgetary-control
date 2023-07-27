import {create} from "zustand";
import axios from "axios";
import {Entry} from "../model/Entry.ts";
import {EntryWithNoId} from "../model/EntryWithNoId.ts";
import {Simulate} from "react-dom/test-utils";
import error = Simulate.error;

type State = {
    entries: Entry[];
    getEntries: () => void;
    createEntry: (requestBody: EntryWithNoId) => void;

}


export const useStore = create<State>((set) => ({
    entries: [],

    getEntries: () => {
        axios.get("/api/entries")
            .then((response) => {
                set({entries: response.data});
            }).catch(error)
    },

    createEntry: (requestBody: EntryWithNoId) => {
        axios.post("/api/entries", requestBody)
            .catch(error)

    }


}));
