import dayjs from 'dayjs';
import { ICategoryLog } from 'app/shared/model/category-log.model';
import { IChanell } from 'app/shared/model/chanell.model';
import { ILinksByCategoryInTop } from 'app/shared/model/links-by-category-in-top.model';

export interface ICategoryList {
  id?: number;
  name?: string | null;
  countChanellInCategory?: number | null;
  isDelete?: boolean | null;
  date1?: string | null;
  date2?: string | null;
  long1?: number | null;
  string1?: string | null;
  boolean1?: boolean | null;
  categoryLogs?: ICategoryLog[] | null;
  chanellIds?: IChanell[] | null;
  linksByCategoryInTopIds?: ILinksByCategoryInTop[] | null;
  isFirst?: boolean | null;
  isShow?: boolean | null;
  score?: number | null;
}

export const defaultValue: Readonly<ICategoryList> = {
  isDelete: false,
  boolean1: false,
};
