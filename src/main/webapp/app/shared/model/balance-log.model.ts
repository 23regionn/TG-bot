import dayjs from 'dayjs';
import { IBalance } from 'app/shared/model/balance.model';

export interface IBalanceLog {
  id?: number;
  balace?: number | null;
  userId?: number | null;
  frostSum?: number | null;
  dateLastAddBalance?: string | null;
  dateLastMinusFromBalance?: string | null;
  date1?: string | null;
  date2?: string | null;
  long1?: number | null;
  string1?: string | null;
  boolean1?: boolean | null;
  balance?: IBalance | null;
}

export const defaultValue: Readonly<IBalanceLog> = {
  boolean1: false,
};
