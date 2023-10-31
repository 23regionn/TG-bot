import dayjs from 'dayjs';
import { ICategory } from 'app/shared/model/category.model';

export interface ICategoryLog {
  id?: number;
  name?: string | null;
  countChanellInCategory?: number | null;
  isDelete?: boolean | null;
  date1?: string | null;
  date2?: string | null;
  long1?: number | null;
  string1?: string | null;
  cityId?: number;
  cityName?: string | null;
  chatId?: number | null;
  catId?: number | null;
  dateLog?: string | null;
  score?: number | null;
  boolean1?: boolean | null;
  category?: ICategory | null;
}

export const defaultValue: Readonly<ICategoryLog> = {
  isDelete: false,
  boolean1: false,
};
