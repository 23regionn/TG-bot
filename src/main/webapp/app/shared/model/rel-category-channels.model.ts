export interface IRelCategoryChannels {
  id?: number;
  scoreChannel?: number | null;
  isShowChannel?: boolean | null;
}

export const defaultValue: Readonly<IRelCategoryChannels> = {
  isShowChannel: false,
};
