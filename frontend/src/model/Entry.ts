import {Category} from "./Category.ts";
import {Interval} from "./Interval.ts";

export type Entry = {
    id: string;
    title: string;
    date: string;
    description: string;
    amount: string;
    category: Category;
    interval: Interval;
}