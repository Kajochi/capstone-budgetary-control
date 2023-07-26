import {Entry} from "./model/Entry.ts";
import {useEffect, useState} from "react";
import axios from "axios";
import EntriesList from "./entriesList/EntriesList.tsx";
import './App.css';
import AddIcon from '@mui/icons-material/Add';
import {Button, IconButton} from "@mui/material";
import styled from "@emotion/styled";

export default function App() {

    const [entries, setEntries] = useState<Entry[]>([])

    function getAllEntries() {
        axios.get('/api/entries')
            .then((response) => {
                setEntries(response.data)
            })
    }

    useEffect(() => {
        getAllEntries()
    }, [])

    return (
        <>
            <header>
                <h1 id={"mainTitle"}>Budgetary Control</h1>
            </header>
            <main>
               <StyledIconButton  size={"small"}><AddIcon/></StyledIconButton>
                <EntriesList entries={entries}/>
            </main>
        </>

    )
}

const StyledIconButton = styled(IconButton)`
    background-color: #49e0e3;
    &:hover {
      background-color: #49e0e3;
    transition: all 0.3s;
    transform: scale(1.2);}
    `;

