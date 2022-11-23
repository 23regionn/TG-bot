import dayjs from 'dayjs';
import { ITGUser } from 'app/shared/model/tg-user.model';

export interface ITGUserLog {
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
  tGUser?: ITGUser | null;
}

export const defaultValue: Readonly<ITGUserLog> = {
  isAdmin: false,
  isBlocked: false,
  isDelete: false,
  boolean1: false,
};
