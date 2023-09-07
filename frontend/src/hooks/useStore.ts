import {create} from "zustand";
import axios from "axios";
import {Entry} from "../model/Entry.ts";
import {EntryWithNoId} from "../model/EntryWithNoId.ts";
import {Simulate} from "react-dom/test-utils";
import error = Simulate.error;
import {FinanceReport} from "../model/FinanceReport.ts";
import {MonthlyBalance} from "../model/MonthlyBalance.ts";

type State = {
    entries: Entry[];
    financeReports: FinanceReport[];
    monthlyBalances: Record<string, MonthlyBalance>;
    selectedMonthYear: string | null;
    getEntries: () => void;
    getFinanceReports: () => void;
    getMonthlyBalances: () => void;
    createEntry: (requestBody: EntryWithNoId) => void;
    updateEntry: (requestBody: EntryWithNoId, id: string) => void;
    deleteEntry: (id: string) => void;
    isCardUpdated: boolean;
    updatedCardId: string;
    setIsCardUpdated: (updated: boolean) => void;
    setUpdatedCardId: (id: string) => void;
    getIsCardUpdated: () => boolean;
    getUpdatedCard: () => Entry | undefined;
    setSelectedMonthYear: (monthYear: string | null) => void;


}


export const useStore = create<State>((set, get) => ({
    entries: [],
    financeReports: [],
    monthlyBalances: {} as Record<string, MonthlyBalance >,
    isCardUpdated: false,
    updatedCardId: "",
    selectedMonthYear: null,



    getEntries: () => {
        axios.get("/api/entries")
            .then((response) => {
                set({entries: response.data});
            }).catch(error)
    },

    getFinanceReports: () => {
        axios.get("/api/financeReports")
            .then((response) => {
                set({financeReports: response.data});
            }).catch(error)
    },

    getMonthlyBalances: () => {
        axios.get("/api/monthlybalance")
            .then((response) => {
                set({monthlyBalances: response.data});
            }).catch(error)
    },

    createEntry: (requestBody: EntryWithNoId) => {
        axios.post("/api/entries", requestBody)
            .catch(error)
            .then(() => {
            })

    },

    updateEntry: (requestBody: EntryWithNoId, id: string) => {
        axios.put(
            "/api/entries/" + id,
            requestBody
        ).catch(error)
            .finally(()=> {
                set({isCardUpdated: false})
            })

    },

    deleteEntry: (id: string) => {

        axios.delete("/api/entries/" + id)
            .catch(error)
            .then(() => {
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
        const getEntries = get().getEntries
        getEntries()
        const id = get().updatedCardId
        const entries = get().entries
        return entries.find(entry => entry.id === id)
    },

    setSelectedMonthYear: (monthYear: string | null) => {
        set({selectedMonthYear: monthYear})
    }


}));
