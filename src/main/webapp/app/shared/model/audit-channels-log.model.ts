import dayjs from 'dayjs';

export interface IAuditChannelsLog {
  id?: number;
  dateLog?: string | null;
  comment?: string | null;
  contacts?: string | null;
  endPublicDate?: string | null;
  idChannel?: number | null;
  isModerate?: boolean | null;
  isPay?: boolean | null;
  lastPayDate?: string | null;
  link?: string | null;
  nameChannel?: string | null;
  priceForPay?: string | null;
  startDate?: string | null;
  countSubscribers?: number | null;
  countViews?: number | null;
}

export const defaultValue: Readonly<IAuditChannelsLog> = {
  isModerate: false,
  isPay: false,
};
