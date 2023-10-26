import dayjs from 'dayjs';

export interface ICountChannelClickPageLog {
  id?: number;
  chatId?: number | null;
  dateLog?: string | null;
  idChannel?: number | null;
  pageNumber?: number | null;
  idCategory?: number | null;
  idCity?: number | null;
}

export const defaultValue: Readonly<ICountChannelClickPageLog> = {};
