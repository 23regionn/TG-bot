export interface IRelCategoryCityChannels {
  id?: number;
  scoreChannel?: number | null;
  isShowChannel?: boolean | null;
}

export const defaultValue: Readonly<IRelCategoryCityChannels> = {
  isShowChannel: false,
};
