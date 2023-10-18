export interface IRelCategoryCityChannels {
  id?: number;
  scoreChannel?: number | null;
  isShowChannel?: boolean | null;
  comment?: string | null;
}

export const defaultValue: Readonly<IRelCategoryCityChannels> = {
  isShowChannel: false,
};
