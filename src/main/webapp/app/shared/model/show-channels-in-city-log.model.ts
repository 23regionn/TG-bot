import dayjs from 'dayjs';

export interface IShowChannelsInCityLog {
  id?: number;
  idChannel?: number | null;
  nameChannel?: string | null;
  idCategory?: number | null;
  nameCategory?: string | null;
  idCity?: number | null;
  nameCity?: string | null;
  isShowChannel?: boolean | null;
  scoreChannel?: number | null;
  comment?: string | null;
  dateLog?: string | null;
}

export const defaultValue: Readonly<IShowChannelsInCityLog> = {
  isShowChannel: false,
};
