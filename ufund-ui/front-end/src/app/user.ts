import { FundingBasket } from "./fundingbasket";

export interface User {
    id: number;
    name: string;
    basket: FundingBasket[];
  }