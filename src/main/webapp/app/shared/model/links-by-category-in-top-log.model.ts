import dayjs from 'dayjs';
import { ILinksByCategoryInTop } from 'app/shared/model/links-by-category-in-top.model';

export interface ILinksByCategoryInTopLog {
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
  linksByCategoryInTop?: ILinksByCategoryInTop | null;
}

export const defaultValue: Readonly<ILinksByCategoryInTopLog> = {
  showLink: false,
  isDelete: false,
  boolean1: false,
};
