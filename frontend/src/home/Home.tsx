import EntriesList from "../entriesList/EntriesList.tsx";
import styled from "@emotion/styled";

export default function Home() {
    return (
        <div>
            <StyledH2>Home</StyledH2>
            <EntriesList/>
        </div>
    )
}

const StyledH2 = styled.h2`
  font-family: "Roboto Light", sans-serif;
  display: flex;
  justify-content: center;
    `;