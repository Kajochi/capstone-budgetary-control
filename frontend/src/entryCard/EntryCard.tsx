import {Entry} from "../model/Entry.ts";
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import Typography from '@mui/material/Typography';

type Props = {
    entry: Entry;
}
export default function EntryCard(props: Props) {



    return (
        <>
            <Card sx={{minWidth: 275, margin: '16px'}}>
                <CardContent style={{background: "#49e0e3", display: "flex", flexDirection: "row"}}>
                    <div style={{display: "flex", flexDirection: "column"}}>
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

                    </div>
                    <div style={{display: 'flex', flexDirection: 'column', marginLeft: 'auto'}}>
                        <Typography variant="h5">
                            {props.entry.amount}€
                        </Typography>
                        <Typography variant="body2">
                            {props.entry.date}
                        </Typography>
                    </div>
                </CardContent>

            </Card>
        </>
    )
}
