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
    return (
        <div>
            <StyledCard>
                <StyledCardContent>
                    <Typography>
                        {financeReport.period}
                    </Typography>
                    <Typography>
                        {financeReport.totalIncome}
                    </Typography>
                    <Typography>
                        {financeReport.totalExpense}
                    </Typography>
                    <Typography>
                        {financeReport.fixCosts}
                    </Typography>
                    <Typography>
                        {financeReport.variableCosts}
                    </Typography>
                    <Typography>
                        {financeReport.balance}
                    </Typography>
                </StyledCardContent>
            </StyledCard>
        </div>
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
