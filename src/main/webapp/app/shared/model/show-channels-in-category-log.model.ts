import dayjs from 'dayjs';

export interface IShowChannelsInCategoryLog {
  id?: number;
  idChannel?: number | null;
  nameChannel?: string | null;
  idCategory?: number | null;
  nameCategory?: string | null;
  isShowChannel?: boolean | null;
  scoreChannel?: number | null;
  comment?: string | null;
  dateLog?: string | null;
}

export const defaultValue: Readonly<IShowChannelsInCategoryLog> = {
  isShowChannel: false,
};
