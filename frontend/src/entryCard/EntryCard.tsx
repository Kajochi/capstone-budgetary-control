import {Entry} from "../model/Entry.ts";
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import Typography from '@mui/material/Typography';
import styled from "@emotion/styled";
import {useNavigate} from "react-router-dom";
import {useStore} from "../hooks/useStore.ts";

type Props = {
    entry: Entry;
}
export default function EntryCard(props: Props) {

    const navigate = useNavigate()

    const setIsCardUpdated  = useStore((state) => state.setIsCardUpdated)
    const setUpdatedCardId = useStore((state) => state.setUpdatedCardId)
function handleClick() {
    navigate("/add-entry/")
    setIsCardUpdated(true)
    setUpdatedCardId(props.entry.id)
}

    return (
        <>
            <StyledCard onClick={handleClick}>
                <StyledCardContent>
                    <StyledDivLeftSide>
                        <Typography variant="h5" gutterBottom>
                            {props.entry.title}
                        </Typography>
                        <Typography variant="body2">
                            {props.entry.description}
                        </Typography>
                        <Typography sx={{mb: 1.5}} color="text.secondary">
                            {props.entry.interval}
                        </Typography>
                        <Typography variant="body2">
                            {props.entry.category}
                        </Typography>
                        <Typography variant="body2">
                            {props.entry.costType}
                        </Typography>

                    </StyledDivLeftSide>
                    <StyledDivRightSide>
                        <Typography variant="h5">
                            {props.entry.amount}€
                        </Typography>
                        <Typography variant="body2">
                            {props.entry.date}
                        </Typography>
                    </StyledDivRightSide>
                </StyledCardContent>

            </StyledCard>
        </>
    )
}

const StyledCard = styled(Card)`
    min-width: 275px;
    margin: 16px;
    background: #49e0e3;
    
    ;`
const StyledCardContent = styled(CardContent)`
    display: flex;
    flex-direction: row;
    ;`
const StyledDivLeftSide = styled.div`
    display: flex;
    flex-direction: column;
    ;`

const StyledDivRightSide = styled.div`
    display: flex;
    flex-direction: column;
    margin-left: auto;
    ;`