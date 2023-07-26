
import AddIcon from '@mui/icons-material/Add';
import {IconButton} from "@mui/material";
import styled from "@emotion/styled";
import {Route, Routes, Link} from "react-router-dom";
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


            <Link to={"/add-entry"}>
                <StyledIconButton size={"small"}><AddIcon/></StyledIconButton>
            </Link>
            <SytledLink to={"/"}>
                Home
            </SytledLink>


        </>

    )
}

const StyledH1 = styled.h1`

  font-family: "Roboto Light", sans-serif;
  display: flex;
  justify-content: center;
`;

const StyledIconButton = styled(IconButton)`
  background-color: #49e0e3;

  &:hover {
    background-color: #49e0e3;
    transition: all 0.3s;
    transform: scale(1.2);
  }
`;

const SytledLink = styled(Link)`
  font-family: "Roboto Light", sans-serif;
  display: flex;
  justify-content: center;
    `;
