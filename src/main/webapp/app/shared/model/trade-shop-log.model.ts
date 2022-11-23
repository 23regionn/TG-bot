import dayjs from 'dayjs';
import { ITradeShop } from 'app/shared/model/trade-shop.model';

export interface ITradeShopLog {
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
  tradeShop?: ITradeShop | null;
}

export const defaultValue: Readonly<ITradeShopLog> = {
  isDelete: false,
  boolean1: false,
};
