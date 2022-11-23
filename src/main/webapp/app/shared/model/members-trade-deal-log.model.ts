import dayjs from 'dayjs';
import { IMembersTradeDeal } from 'app/shared/model/members-trade-deal.model';

export interface IMembersTradeDealLog {
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
  membersTradeDeal?: IMembersTradeDeal | null;
}

export const defaultValue: Readonly<IMembersTradeDealLog> = {
  isWinner: false,
  isDelete: false,
  boolean1: false,
};
