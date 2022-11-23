import dayjs from 'dayjs';
import { IChanell } from 'app/shared/model/chanell.model';

export interface IChanellLog {
  id?: number;
  name?: string | null;
  link?: string | null;
  score?: number | null;
  status?: string | null;
  countSubscribers?: number | null;
  quailityFromAnotherSources?: number | null;
  priceDiapozon?: number | null;
  isModerate?: boolean | null;
  showChanellInTopByCategory?: boolean | null;
  region?: string | null;
  city?: string | null;
  isDelete?: boolean | null;
  currentDate?: string | null;
  date1?: string | null;
  date2?: string | null;
  long1?: number | null;
  string1?: string | null;
  boolean1?: boolean | null;
  chanell?: IChanell | null;
}

export const defaultValue: Readonly<IChanellLog> = {
  isModerate: false,
  showChanellInTopByCategory: false,
  isDelete: false,
  boolean1: false,
};
