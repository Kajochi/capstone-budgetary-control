import {Entry} from "../model/entry.ts";
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import Typography from '@mui/material/Typography';
type Props = {
    entry: Entry;
}
export default function EntryCard(props: Props) {

    return (
        <>
            <Card  sx={{ minWidth: 275, margin: '16px' }}>
                <CardContent style={{background:"lightblue"}}>

                    <Typography sx={{ fontSize: 14 }} color="text.secondary" gutterBottom>
                        {props.entry.title}
                    </Typography>
                    <Typography variant="h5" component="div">
                        {props.entry.description}
                    </Typography>
                    <Typography sx={{ mb: 1.5 }} color="text.secondary">
                        {props.entry.interval}
                    </Typography>
                    <Typography variant="body2">
                        {props.entry.amount} EUR
                    </Typography>
                    <Typography variant="body2">
                        {props.entry.category}
                    </Typography>
                    <Typography variant="body2">
                        {props.entry.date}
                    </Typography>
                </CardContent>

            </Card>
        </>
    )
}
