import dayjs from 'dayjs';
import { IOfferFromCostumersLog } from 'app/shared/model/offer-from-costumers-log.model';
import { ITGUser } from 'app/shared/model/tg-user.model';

export interface IOfferFromCostumers {
  id?: number;
  text?: string | null;
  isDelete?: boolean | null;
  adminId?: number | null;
  isActive?: boolean | null;
  date1?: string | null;
  date2?: string | null;
  long1?: number | null;
  string1?: string | null;
  boolean1?: boolean | null;
  offerFromCostumersLogs?: IOfferFromCostumersLog[] | null;
  tGUser?: ITGUser | null;
}

export const defaultValue: Readonly<IOfferFromCostumers> = {
  isDelete: false,
  isActive: false,
  boolean1: false,
};
