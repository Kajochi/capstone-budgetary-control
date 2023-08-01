import {Interval} from "./Interval.ts";
import {Category} from "./Category.ts";
import {CostType} from "./CostType.ts";

export type EntryWithNoId = {
    title: string;
    description: string;
    date: string;
    amount: string;
    interval: Interval;
    category: Category;
    costType: CostType;
}