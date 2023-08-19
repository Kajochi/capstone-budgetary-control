import {Entry} from "../model/Entry.ts";
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import Typography from '@mui/material/Typography';
import styled from "@emotion/styled";
import {useNavigate} from "react-router-dom";
import {useStore} from "../hooks/useStore.ts";
import {IconButton} from "@mui/material";
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';
import ExpandLessIcon from '@mui/icons-material/ExpandLess';
import {useState} from "react";
import {Category} from "../model/Category.ts";
import EditIcon from '@mui/icons-material/Edit';


type Props = {
    entry: Entry;
    monthYear: string;
}
export default function EntryCard(props: Props) {

    const [expanded, setExpanded] = useState(false);
    const navigate = useNavigate()

    const setIsCardUpdated = useStore((state) => state.setIsCardUpdated)
    const setUpdatedCardId = useStore((state) => state.setUpdatedCardId)

    function handleClick() {
        navigate(`/add-entry/?monthYear=${props.monthYear}`)
        setIsCardUpdated(true)
        setUpdatedCardId(props.entry.id)
    }

    function toggleExpanded() {
        setExpanded(!expanded)
    }

    function castMonthToNumber(month: string) {
        switch (month) {
            case "JANUARY":
                return "01."
            case "FEBRUARY":
                return "02."
            case "MARCH":
                return "03."
            case "APRIL":
                return "04."
            case "MAY":
                return "05."
            case "JUNE":
                return "06."
            case "JULY":
                return "07."
            case "AUGUST":
                return "08."
            case "SEPTEMBER":
                return "09."
            case "OCTOBER":
                return "10."
            case "NOVEMBER":
                return "11."
            case "DECEMBER":
                return "12."
        }
    }

    const customDate = props.entry.date.split("-")[2] + "." + castMonthToNumber(props.monthYear.split("-")[0])

    return (
        <>
            <StyledCard>
                <StyledCardContent>
                    <RegularCardContent>

                        <Typography variant="h5" gutterBottom>
                            {props.entry.title}
                        </Typography>
                        <AmountTypography variant="h5" entry={props.entry}>
                            {props.entry.amount}€
                        </AmountTypography>
                    </RegularCardContent>
                    <Typography variant="h6">
                        {customDate}
                    </Typography>
                    <ButtonDiv>
                        <EditIcon onClick={handleClick}/>
                        <IconButton onClick={toggleExpanded}>
                            {expanded ? <ExpandLessIcon/> : <ExpandMoreIcon/>}
                        </IconButton>
                    </ButtonDiv>
                    {expanded && (
                        <ExpandedCardContent>
                            <DescriptionDiv>
                                <DescriptionTypography>
                                    Description:
                                </DescriptionTypography>
                                <Typography variant="body2">
                                    {props.entry.description}
                                </Typography>
                            </DescriptionDiv>
                            <IntervalDiv>
                            <IntervalTypography>
                                Interval:
                            </IntervalTypography>
                                <Typography>
                                    {props.entry.interval}
                                </Typography>
                            </IntervalDiv>
                            <CosttypeDiv>
                            <CosttypeTypography>
                                Cost Type:
                            </CosttypeTypography>
                            <Typography >
                                {props.entry.costType}
                            </Typography>
                            </CosttypeDiv>
                        </ExpandedCardContent>
                    )}
                </StyledCardContent>
            </StyledCard>
        </>
    )
}

const StyledCard = styled(Card)`
  min-width: 275px;
  margin: 16px;
  background: #edf0fc;
  box-shadow: 10px 10px 5px -4px silver;;`
const StyledCardContent = styled(CardContent)`
  display: flex;
  flex-direction: column;;`
const RegularCardContent = styled.div`
  display: flex;
  justify-content: space-between;
  flex-direction: row;;`

const ExpandedCardContent = styled.div`
  display: flex;
  flex-direction: column;
  ;`

const ButtonDiv = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: space-between;
`;

interface AmountTypographyProps {
    entry: {
        category: Category;
    };
}

const AmountTypography = styled(Typography)<AmountTypographyProps>`
  color: ${props => (props.entry?.category === 'EXPENSE' ? 'red' : 'green')};;`

const DescriptionTypography = styled(Typography)`
  color: black;
  font-family: "Roboto Light", sans-serif;
  font-size: 16px;
`;
const DescriptionDiv = styled.div`
  display: flex;
  flex-direction: row;
    justify-content: space-between;
`;

const IntervalTypography = styled(Typography)`
    color: black;
    font-family: "Roboto Light", sans-serif;
    font-size: 16px;
    `;

const IntervalDiv = styled.div`
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    `;

const CosttypeTypography = styled(Typography)`
    color: black;
    font-family: "Roboto Light", sans-serif;
    font-size: 16px;
    `;

const CosttypeDiv = styled.div`
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    `;
