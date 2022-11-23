import dayjs from 'dayjs';
import { IMembersTradeDealLog } from 'app/shared/model/members-trade-deal-log.model';
import { ITradeShop } from 'app/shared/model/trade-shop.model';

export interface IMembersTradeDeal {
  id?: number;
  tgUserIdCurrent?: number | null;
  priceOffer?: number | null;
  currentDate?: string | null;
  isWinner?: boolean | null;
  isDelete?: boolean | null;
  date1?: string | null;
  date2?: string | null;
  long1?: number | null;
  string1?: string | null;
  boolean1?: boolean | null;
  membersTradeDealLogs?: IMembersTradeDealLog[] | null;
  tradeShop?: ITradeShop | null;
}

export const defaultValue: Readonly<IMembersTradeDeal> = {
  isWinner: false,
  isDelete: false,
  boolean1: false,
};
