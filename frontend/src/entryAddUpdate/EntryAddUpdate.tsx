import styled from "@emotion/styled";
import {
    Button,
    FormControl,
    InputLabel, MenuItem,
    Select,
    TextField,
    ToggleButton,
    ToggleButtonGroup
} from "@mui/material";
import React, {useEffect, useState} from "react";
import {Category} from "../model/Category.ts";
import {Interval} from "../model/Interval.ts";
import {useStore} from "../hooks/useStore.ts";
import {useNavigate,  useSearchParams} from "react-router-dom";
import {CostType} from "../model/CostType.ts";



export default function EntryAddUpdate() {




    const [interval, setInterval] = useState<Interval>('ONCE');
    const [title, setTitle] = useState<string>("")
    const [description, setDescription] = useState<string>("")
    const [amount, setAmount] = useState<string>( "")
    const [date, setDate] = useState<string>("")
    const [category, setCategory] = useState<Category>( "INCOME")
    const [costType, setCostType] = useState<CostType>("FIXED")

    const [selectedMonthYear] = useSearchParams()
    const monthYear = selectedMonthYear.get("monthYear")

    const navigate = useNavigate()
    const getIsCardUpdated = useStore((state) => state.getIsCardUpdated)
    const setIsCardUpdated = useStore((state) => state.setIsCardUpdated)

    const createEntry = useStore((state) => state.createEntry)
    const updateEntry = useStore((state) => state.updateEntry)
    const deleteEntry = useStore((state) => state.deleteEntry)
    const getUpdatedCard = useStore((state) => state.getUpdatedCard)

    useEffect(() => {
        const updatedCard = getUpdatedCard()
        if (updatedCard && getIsCardUpdated()) {
            setInterval(updatedCard.interval)
            setTitle(updatedCard.title)
            setDescription(updatedCard.description)
            setAmount(updatedCard.amount)
            setDate(updatedCard.date)
            setCategory(updatedCard.category)
            setCostType(updatedCard.costType)
        }
    }, [])


    function resetAllUseStates() {
        setTitle("")
        setDescription("")
        setAmount("")
        setDate("")
        setInterval("ONCE")
        setCategory("INCOME")
        setCostType("FIXED")
    }

    function handleSubmit(event: React.FormEvent<HTMLFormElement>) {
        event.preventDefault()
        getIsCardUpdated()? handlePut() : handlePost()

    }

    function handlePost() {
        const requestBody = {
            title: title,
            description: description,
            amount: amount.replace(/,/, "."),
            date: date,
            interval: interval,
            category: category,
            costType: costType
        }
        createEntry(requestBody)
        navigate(`/?monthYear=${monthYear}`)

        resetAllUseStates()

    }

    function handlePut() {

        const requestBody = {
            title: title,
            description: description,
            amount: amount,
            date: date,
            interval: interval,
            category: category,
            costType: costType
        }
        updateEntry(requestBody, getUpdatedCard()?.id as string)
        navigate(`/?monthYear=${monthYear}`)
        resetAllUseStates()
    }

    function handleDelete() {
        deleteEntry(getUpdatedCard()?.id as string)
        navigate(`/?monthYear=${monthYear}`)
        resetAllUseStates()
    }

    function handleChangeCategory(_: React.MouseEvent<HTMLElement>, newCategory: Category) {
        setCategory(newCategory)
    }

    function handleChangeCostType(_: React.MouseEvent<HTMLElement>, newCostType: CostType) {
        setCostType(newCostType)
    }
    function handleCancel() {
        navigate(`/?monthYear=${monthYear}`)
        resetAllUseStates()
        setIsCardUpdated(false)
    }




    return (
        <div>
            <StyledH2>Add Entry</StyledH2>

            <StyledForm onSubmit={handleSubmit}>

                <StyledTextField required id="title" label="Title" variant="outlined" value={title}
                                 onChange={(e) => setTitle(e.target.value)}/>
                <StyledTextField required id="description" label="Description" variant="outlined" value={description}
                                 onChange={(e) => setDescription(e.target.value)}/>
                <StyledTextField required id="amount" label="Amount" variant="outlined" value={amount}
                                 inputProps={{steps: "0.01"}}
                                 onChange={(e) => setAmount(e.target.value)}/>
                <StyledTextField required id="date" variant="outlined" type="date" value={date}
                                 onChange={(e) => setDate(e.target.value)}/>
                <StyledDiv>

                    <FormControl>
                        <InputLabel id="intervall">Interval</InputLabel>
                        <Select label="intervall"
                                value={interval}
                                onChange={(e) => setInterval(e.target.value as Interval)}
                        >
                            <MenuItem value="ONCE">Once</MenuItem>
                            <MenuItem value="MONTHLY">Monthly</MenuItem>
                            <MenuItem value="QUARTERLY">Quarterly</MenuItem>
                            <MenuItem value="HALF_YEARLY">Half Yearly</MenuItem>
                            <MenuItem value="YEARLY">Yearly</MenuItem>
                        </Select>
                    </FormControl>

                    <StyledToggleGroup id="category" color="primary" value={category} exclusive
                                       onChange={handleChangeCategory}
                                       aria-label="Platform">
                        <StyledToggleButton value="INCOME">Income</StyledToggleButton>
                        <StyledToggleButton value="EXPENSE">Expense</StyledToggleButton>
                    </StyledToggleGroup>

                    <StyledToggleGroup id="costType" color="primary" value={costType} exclusive
                                       onChange={handleChangeCostType}
                                       aria-label="Platform">
                        <StyledToggleButton value="FIXED">Fixed</StyledToggleButton>
                        <StyledToggleButton value="VARIABLE">Variable</StyledToggleButton>
                    </StyledToggleGroup>
                </StyledDiv>
                <StyleDivButtons>
                    { getIsCardUpdated() ?
                        (<>
                            <StyledButton type="submit">Save</StyledButton>
                         <StyledButton onClick={handleDelete}>Delete</StyledButton>  </> ) :
                        (<StyledButton type="submit">Add</StyledButton>)
                    }

                    <StyledButton onClick={handleCancel}>Cancel</StyledButton>
                </StyleDivButtons>
            </StyledForm>
        </div>
    )
}

const StyledForm = styled.form`

`;

const StyledDiv = styled.div`
  display: flex;
  flex-direction: column;
  margin: 16px;
`;
const StyledH2 = styled.h2`
  font-family: "Roboto Light", sans-serif;
  display: flex;
  justify-content: center;
`;

const StyledTextField = styled(TextField)`
  font-family: "Roboto Light", sans-serif;
  display: flex;
  justify-content: center;
  margin: 16px;
`;


const StyledToggleButton = styled(ToggleButton)`
  font-family: "Roboto Light", sans-serif;
  display: flex;
  justify-content: center;

`;

const StyledToggleGroup = styled(ToggleButtonGroup)`
  font-family: "Roboto Light", sans-serif;
  display: flex;
  justify-content: center;
  margin: 16px;
`;

const StyledButton = styled(Button)`
  font-family: "Roboto Light", sans-serif;
  display: flex;
  justify-content: center;
  margin: 16px;
  border: 1px solid black;
  width: 130px;
`;

const StyleDivButtons = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: center;
`;
