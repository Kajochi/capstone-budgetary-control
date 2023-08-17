
import styled from "@emotion/styled";
import AddIcon from "@mui/icons-material/Add";
import {Link, useSearchParams} from "react-router-dom";
import {IconButton} from "@mui/material";
import {useStore} from "../hooks/useStore.ts";
import {useEffect} from "react";


export default function Home() {

    const [selectedMonthYear] = useSearchParams()
    const monthYear = selectedMonthYear.get("monthYear")
    const getEntries = useStore((state) => state.getEntries)

   useEffect(() => {
         getEntries()
   }, [])

    const entries = useStore((state) => state.entries)

    function existsEntries() : boolean {
        return entries.length > 0;
    }

    return (
        <div>
            <StyledH2>Home</StyledH2>
            <IntroductoryText>
                <h4>Welcome to Budgetary Control!</h4>
                This is a simple app that helps you keep track of your expenses and
                incomes.<br/>
                You can add entries, view your monthly balance and generate a finance report.
            </IntroductoryText>
            <ExplanationText>
                <h4>How to use this app?</h4>
            {existsEntries() ? (
                <p>You have already created transactions.
                    Click on the burger menu at the top right to display the balance sheet for a specific month, for example.</p>
            ) : (
                <p>You have not yet created any transactions.
                    Click on the plus symbol below or in the burger menu on the top right on Add entry to create transactions.</p>
            )}
            </ExplanationText>
            <UpcomingFeatures>
                <h4>Upcoming Features</h4>
                <ul>
                    <li>Sign up</li>
                    <li>Log In / Log Out</li>
                    <li>Excel table export</li>
                    <li>Ideas for saving money</li>
                    <li>Import your bills</li>
                    <li>Dark Mode</li>
                </ul>
            </UpcomingFeatures>
            <StyledLink to={`/add-entry/?monthYear=${monthYear}`}>
                <StyledIconButton size={"small"}><AddIcon/></StyledIconButton>
            </StyledLink>


        </div>
    )
}


const StyledH2 = styled.h2`
  font-family: "Roboto Light", sans-serif;
  display: flex;
  justify-content: center;
    `;

const StyledLink = styled(Link)`
    display: flex;
    justify-content: center;
  `;

const StyledIconButton = styled(IconButton)`
  background-color: #f62929;
  display: flex;
  justify-content: center;

  &:hover {
    background-color: #129cfc;
    transition: all 0.3s;
    transform: scale(1.2);
  }
`;

const IntroductoryText = styled.div`
  padding: 16px;
  border-radius: 7px;
  background-color: #33ec97;
  font-family: "Roboto Light", sans-serif;
  display: flex;
  flex-direction: column;
  justify-content: center;
  margin: 16px;
  font-size: 16px;
  font-weight: 500;
  line-height: 1.6;
  letter-spacing: 0.0075em;
  text-align: center;
 
`;

const ExplanationText = styled.div`
  padding: 16px;
  border-radius: 7px;
  background-color: #c25aee;
  font-family: "Roboto Light", sans-serif;
  display: flex;
  flex-direction: column;
  justify-content: center;
  margin: 16px;
  font-size: 16px;
  font-weight: 500;
  line-height: 1.6;
  letter-spacing: 0.0075em;
  text-align: center;
`;

const UpcomingFeatures = styled.div`
  padding: 16px;
  border-radius: 7px;
  background-color: #f15e2f;
  font-family: "Roboto Light", sans-serif;
  display: flex;
  flex-direction: column;
  justify-content: center;
  margin: 16px;
  font-size: 16px;
  font-weight: 500;
  line-height: 1.6;
  letter-spacing: 0.0075em;
  text-align: center;
`;