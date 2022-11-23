import dayjs from 'dayjs';
import { IMembersTradeDeal } from 'app/shared/model/members-trade-deal.model';
import { ITradeShopLog } from 'app/shared/model/trade-shop-log.model';

export interface ITradeShop {
  id?: number;
  category?: string | null;
  priceDiapozon?: number | null;
  currentPrice?: number | null;
  whiceLineFromAllCountLines?: number | null;
  tgUserIdWinner?: number | null;
  inWhatDateWillPostThisLinks?: number | null;
  dateFinishTorgs?: string | null;
  isDelete?: boolean | null;
  date1?: string | null;
  date2?: string | null;
  long1?: number | null;
  string1?: string | null;
  boolean1?: boolean | null;
  membersTradeDeals?: IMembersTradeDeal[] | null;
  tradeShopLogs?: ITradeShopLog[] | null;
}

export const defaultValue: Readonly<ITradeShop> = {
  isDelete: false,
  boolean1: false,
};
