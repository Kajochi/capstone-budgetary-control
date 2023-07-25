import {Entry} from "./model/entry.ts";
import {useEffect, useState} from "react";
import axios from "axios";
import EntriesList from "./entriesList/EntriesList.tsx";




export default function App() {

  const [entries, setEntries] = useState<Entry[]>([])

  function getAllEntries() {
    axios.get('/api/entries').then((response) => {
      setEntries(response.data)
    })
  }

  useEffect(() => {
    getAllEntries()
  }, [])

  return (
    <>
      <header>
        <h1>Budgetary Control</h1>
      </header>
        <main>
          <EntriesList entries={entries} />
        </main>
      </>

  )
}


