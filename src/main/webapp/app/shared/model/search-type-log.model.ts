import dayjs from 'dayjs';

export interface ISearchTypeLog {
  id?: number;
  chatId?: number | null;
  dateLog?: string | null;
  inlineSearch?: boolean | null;
  pageSearch?: boolean | null;
  pageNumber?: number | null;
  idCity?: number | null;
}

export const defaultValue: Readonly<ISearchTypeLog> = {
  inlineSearch: false,
  pageSearch: false,
};
