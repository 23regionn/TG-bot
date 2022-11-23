import dayjs from 'dayjs';
import { ILinksByCategoryInTop } from 'app/shared/model/links-by-category-in-top.model';
import { IChanellLog } from 'app/shared/model/chanell-log.model';
import { ITGUser } from 'app/shared/model/tg-user.model';
import { ICategory } from 'app/shared/model/category.model';

export interface IChanell {
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
  linksByCategoryInTops?: ILinksByCategoryInTop[] | null;
  chanellLogs?: IChanellLog[] | null;
  tGUser?: ITGUser | null;
  categoryIds?: ICategory[] | null;
}

export const defaultValue: Readonly<IChanell> = {
  isModerate: false,
  showChanellInTopByCategory: false,
  isDelete: false,
  boolean1: false,
};
