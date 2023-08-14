
import styled from "@emotion/styled";
import {Route, Routes} from "react-router-dom";
import EntryAddUpdate from "./entryAddUpdate/EntryAddUpdate.tsx";
import Home from "./home/Home.tsx";


export default function App() {



    return (
        <>
            <StyledH1>Budgetary Control</StyledH1>
            <Routes>
                <Route path={"/"} element={<Home/>}/>
                <Route path={"/add-entry"} element={<EntryAddUpdate/>}/>
            </Routes>

        </>

    )
}

const StyledH1 = styled.h1`

  font-family: "Roboto Light", sans-serif;
  display: flex;
  justify-content: center;
`;



