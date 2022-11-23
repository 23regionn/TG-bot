import dayjs from 'dayjs';
import { IOfferFromCostumers } from 'app/shared/model/offer-from-costumers.model';

export interface IOfferFromCostumersLog {
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
  offerFromCostumers?: IOfferFromCostumers | null;
}

export const defaultValue: Readonly<IOfferFromCostumersLog> = {
  isDelete: false,
  isActive: false,
  boolean1: false,
};
