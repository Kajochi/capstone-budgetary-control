import {Period} from "../model/Period.ts";
import {FinanceReport} from "../model/FinanceReport.ts";
import Card from "@mui/material/Card";
import styled from "@emotion/styled";
import CardContent from "@mui/material/CardContent";
import Typography from "@mui/material/Typography";

type Props = {
    period: Period,
    financeReports: FinanceReport[]
}

export default function FinanceReportCard(props: Props){

    let financeReport: FinanceReport

    if (props.period === 'MONTH') {
        financeReport = props.financeReports[0]
    } else if (props.period === 'QUARTER') {
        financeReport = props.financeReports[1]
    } else if (props.period === 'HALF_YEAR') {
        financeReport = props.financeReports[2]
    } else {
        financeReport = props.financeReports[3]
    }

    if (financeReport === undefined) {
        return <div>loading...</div>
    }
    return (
        <div>
            <StyledCard>
                <StyledCardContent>
                    <StyledTypography >
                         {financeReport.period}
                    </StyledTypography>
                    <Typography>
                       Totalincome:  {financeReport.totalIncome}
                    </Typography>
                    <Typography>
                       Totalexpense:  {financeReport.totalExpenses}
                    </Typography>
                    <Typography>
                       Fixcosts:  {financeReport.fixCosts}
                    </Typography>
                    <Typography>
                       Variablecosts:  {financeReport.variableCosts}
                    </Typography>
                    <Typography>
                       Balance:  {financeReport.balance}
                    </Typography>
                </StyledCardContent>
            </StyledCard>
        </div>
    )
}

const StyledTypography = styled(Typography)`
    display: flex;
    justify-content: center;
  
    ;`

const StyledCard = styled(Card)`
    min-width: 275px;
    margin: 16px;
    background: #49e0e3;
    
    ;`
const StyledCardContent = styled(CardContent)`
    display: flex;
    flex-direction: column;
    ;`
