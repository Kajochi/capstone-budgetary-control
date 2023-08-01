import {Category} from "./Category.ts";
import {Interval} from "./Interval.ts";
import {CostType} from "./CostType.ts";

export type Entry = {
    id: string;
    title: string;
    date: string;
    description: string;
    amount: string;
    category: Category;
    interval: Interval;
    costType: CostType
}