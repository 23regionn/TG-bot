import dayjs from 'dayjs';
import { ILinksByCategoryInTopLog } from 'app/shared/model/links-by-category-in-top-log.model';
import { IChanell } from 'app/shared/model/chanell.model';
import { ICategory } from 'app/shared/model/category.model';

export interface ILinksByCategoryInTop {
  id?: number;
  category?: string | null;
  priceDiapozon?: number | null;
  link?: string | null;
  chanellAdminId?: number | null;
  datePostLinkStart?: string | null;
  datePostLinkEnd?: string | null;
  positionBetweenLinks?: number | null;
  showLink?: boolean | null;
  isDelete?: boolean | null;
  date1?: string | null;
  date2?: string | null;
  long1?: number | null;
  string1?: string | null;
  boolean1?: boolean | null;
  linksByCategoryInTopLogs?: ILinksByCategoryInTopLog[] | null;
  chanell?: IChanell | null;
  categoryIds?: ICategory[] | null;
}

export const defaultValue: Readonly<ILinksByCategoryInTop> = {
  showLink: false,
  isDelete: false,
  boolean1: false,
};
