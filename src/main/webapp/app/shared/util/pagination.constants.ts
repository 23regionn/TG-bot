export const ITEMS_PER_PAGE = 20;
export const ITEMS_PER_PAGE100 = 100;
export const ITEMS_PER_PAGE30 = 30;

export interface IPaginator {
  numberPage: number;
  countElement: number;
}

export interface IFilter {
  name: string;
  link: string;
  lastPayDate: string;
  endPublicDate: string;
}
