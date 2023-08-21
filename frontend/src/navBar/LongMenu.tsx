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




export default function LongMenu() {
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
                navigate(`/add-entry`)
                break;
            case "Monthly Balance":
                navigate(`/monthlyBalance`)
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