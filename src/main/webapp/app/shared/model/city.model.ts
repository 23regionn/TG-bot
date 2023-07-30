import dayjs from 'dayjs';

export interface ICity {
  id?: number;
  cityName?: string | null;
  dateCreateCity?: string | null;
  status?: string | null;
}

export const defaultValue: Readonly<ICity> = {};
