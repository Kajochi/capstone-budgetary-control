import EntriesList from "../entriesList/EntriesList.tsx";
import styled from "@emotion/styled";
import AddIcon from "@mui/icons-material/Add";
import {Link} from "react-router-dom";
import {IconButton} from "@mui/material";
import FinanceReport from "../financeReport/FinanceReport.tsx";

export default function Home() {
    return (
        <div>
            <StyledH2>Home</StyledH2>
            <Link to={"/add-entry"}>
                <StyledIconButton size={"small"}><AddIcon/></StyledIconButton>
            </Link>
            <EntriesList/>
            <FinanceReport/>

        </div>
    )
}

const StyledH2 = styled.h2`
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
