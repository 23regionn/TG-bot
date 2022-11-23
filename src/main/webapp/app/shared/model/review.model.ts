import dayjs from 'dayjs';
import { ITGUser } from 'app/shared/model/tg-user.model';

export interface IReview {
  id?: number;
  isActive?: boolean | null;
  text?: string | null;
  userId?: number | null;
  linkToSaller?: string | null;
  adminId?: number | null;
  isNegative?: boolean | null;
  isDelete?: boolean | null;
  date1?: string | null;
  date2?: string | null;
  long1?: number | null;
  string1?: string | null;
  boolean1?: boolean | null;
  tGUser?: ITGUser | null;
}

export const defaultValue: Readonly<IReview> = {
  isActive: false,
  isNegative: false,
  isDelete: false,
  boolean1: false,
};
