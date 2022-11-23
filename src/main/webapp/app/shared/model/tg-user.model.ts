import dayjs from 'dayjs';
import { IBalance } from 'app/shared/model/balance.model';
import { IChanell } from 'app/shared/model/chanell.model';
import { IOfferFromCostumers } from 'app/shared/model/offer-from-costumers.model';
import { IReview } from 'app/shared/model/review.model';
import { IPays } from 'app/shared/model/pays.model';
import { ITGUserLog } from 'app/shared/model/tg-user-log.model';

export interface ITGUser {
  id?: number;
  idTgUser?: number | null;
  firstName?: string | null;
  registrationDate?: string | null;
  userRole?: string | null;
  isAdmin?: boolean | null;
  score?: number | null;
  isBlocked?: boolean | null;
  chatId?: number | null;
  isDelete?: boolean | null;
  date1?: string | null;
  date2?: string | null;
  long1?: number | null;
  string1?: string | null;
  boolean1?: boolean | null;
  balance?: IBalance | null;
  chanells?: IChanell[] | null;
  offerFromCostumers?: IOfferFromCostumers[] | null;
  reviews?: IReview[] | null;
  pays?: IPays[] | null;
  tGUserLogs?: ITGUserLog[] | null;
}

export const defaultValue: Readonly<ITGUser> = {
  isAdmin: false,
  isBlocked: false,
  isDelete: false,
  boolean1: false,
};
