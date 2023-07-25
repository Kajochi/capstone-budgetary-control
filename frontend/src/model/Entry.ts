export type Entry = {
    id: string;
    title: string;
    date: string;
    description: string;
    amount: string;
    category: "INCOME" | "EXPENSE";
    interval: "MONTHLY" | "HALF_YEARLY" | "QUARTERLY" | "YEARLY" | "ONCE";
}