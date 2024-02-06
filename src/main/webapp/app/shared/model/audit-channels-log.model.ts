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
  oldComment?: string | null;
  oldContacts?: string | null;
  oldEndPublicDate?: string | null;
  oldIsModerate?: boolean | null;
  oldIsPay?: boolean | null;
  oldLastPayDate?: string | null;
  oldLink?: string | null;
  oldNameChannel?: string | null;
  oldPriceForPay?: string | null;
  oldStartDate?: string | null;
  oldCountSubscribers?: number | null;
  oldCountViews?: number | null;
}

export const defaultValue: Readonly<IAuditChannelsLog> = {
  isModerate: false,
  isPay: false,
  oldIsModerate: false,
  oldIsPay: false,
};
