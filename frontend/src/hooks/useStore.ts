import {create} from "zustand";
import axios from "axios";
import {Entry} from "../model/Entry.ts";

type State = {
    entries: Entry[];
    getEntries: () => void;

}


export const useStore = create<State>((set) => ({
    entries: [],
    getEntries: () => {
    axios.get("/api/entries")
        .then((response) => {
            set({entries: response.data});
        })
}}));