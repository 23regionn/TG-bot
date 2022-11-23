import dayjs from 'dayjs';
import { IBalanceLog } from 'app/shared/model/balance-log.model';
import { ITGUser } from 'app/shared/model/tg-user.model';

export interface IBalance {
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
  balanceLogs?: IBalanceLog[] | null;
  tGUser?: ITGUser | null;
}

export const defaultValue: Readonly<IBalance> = {
  boolean1: false,
};
