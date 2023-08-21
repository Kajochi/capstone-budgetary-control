import * as React from 'react';
import IconButton from '@mui/material/IconButton';
import Menu from '@mui/material/Menu';
import MenuItem from '@mui/material/MenuItem';
import MenuIcon from '@mui/icons-material/Menu';
import {useState} from "react";
import {useNavigate} from "react-router-dom";

const options = [
    'Home',
    'Monthly Balance',
    'Finance Report',
    'Add Entry',

];

type Props = {
    monthYear: string | null;
}

const ITEM_HEIGHT = 48;

export default function LongMenu(props: Props) {
    const [anchorEl, setAnchorEl] = useState<null | HTMLElement>(null);
    const open = Boolean(anchorEl);

    const navigate = useNavigate()
    const handleClick = (event: React.MouseEvent<HTMLElement>) => {
        setAnchorEl(event.currentTarget);
    };
    const handleClickOnItem = (event: React.MouseEvent<HTMLElement>) => {
        setAnchorEl(null);
        const item = event.currentTarget.textContent
        switch (item) {
            case "Home":
                navigate("/")
                break;
            case "Add Entry":
                navigate(`/add-entry/?monthYear=${props.monthYear}`)
                break;
            case "Monthly Balance":
                navigate(`/monthlyBalance/?monthYear=${props.monthYear}`)
                break;
            case "Finance Report":
                navigate("/financeReport")
                break;
        }
    };

    return (
        <div>
            <IconButton
                aria-label="more"
                id="long-button"
                aria-controls={open ? 'long-menu' : undefined}
                aria-expanded={open ? 'true' : undefined}
                aria-haspopup="true"
                onClick={handleClick}
            >
                <MenuIcon/>
            </IconButton>
            <Menu
                id="long-menu"
                MenuListProps={{
                    'aria-labelledby': 'long-button',
                }}
                anchorEl={anchorEl}
                open={open}
                onClose={handleClickOnItem}
                PaperProps={{
                    style: {
                        maxHeight: ITEM_HEIGHT * 4.5,
                        width: '20ch',
                    },
                }}
            >
                {options.map((option) => (
                    <MenuItem key={option} value={option} onClick={handleClickOnItem}>
                        {option}
                    </MenuItem>
                ))}
            </Menu>
        </div>
    );
}