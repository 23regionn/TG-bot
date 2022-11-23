import dayjs from 'dayjs';
import { ITGUser } from 'app/shared/model/tg-user.model';

export interface IPays {
  id?: number;
  datePaysSubscriptions?: string | null;
  link?: string | null;
  sumForPays?: number | null;
  typeBuy?: string | null;
  category?: string | null;
  typeBuyLong?: number | null;
  date1?: string | null;
  date2?: string | null;
  long1?: number | null;
  string1?: string | null;
  boolean1?: boolean | null;
  tGUser?: ITGUser | null;
}

export const defaultValue: Readonly<IPays> = {
  boolean1: false,
};
