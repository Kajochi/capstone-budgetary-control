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
    updateEntry: (requestBody: EntryWithNoId, id: string) => void;
    deleteEntry: (id: string) => void;
    isCardUpdated: boolean;
    updatedCardId: string;
    setIsCardUpdated: (updated: boolean) => void;
    setUpdatedCardId: (id: string) => void;
    getIsCardUpdated: () => boolean;
    getUpdatedCard: () => Entry | undefined;
}


export const useStore = create<State>((set, get) => ({
    entries: [],
    isCardUpdated: false,
    updatedCardId: "",


    getEntries: () => {
        axios.get("/api/entries")
            .then((response) => {
                set({entries: response.data});
            }).catch(error)
    },

    createEntry: (requestBody: EntryWithNoId) => {
        const getEntries = get().getEntries
        axios.post("/api/entries", requestBody)
            .catch(error)
            .then(() => {
                getEntries();
            })

    },

    updateEntry: (requestBody: EntryWithNoId, id: string) => {
        const getEntries = get().getEntries
        axios.put(
            "/api/entries/" + id,
            requestBody
        ).catch(error)
            .finally(()=> {
                getEntries();
                set({isCardUpdated: false})
            })

    },

    deleteEntry: (id: string) => {
        const getEntries = get().getEntries
        axios.delete("/api/entries/" + id)
            .catch(error)
            .then(() => {
                getEntries();
                set({isCardUpdated: false})
            })
    },

    setIsCardUpdated: (updated: boolean) => {
        set({isCardUpdated: updated})
    },

    setUpdatedCardId: (id: string) => {
        set({updatedCardId: id})
    },

    getIsCardUpdated: () => {
        return get().isCardUpdated
    },

    getUpdatedCard: () => {
        const id = get().updatedCardId
        const entries = get().entries
        return entries.find(entry => entry.id === id)
    }


}));
