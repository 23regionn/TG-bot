export interface IRelCategoryCity {
  id?: number;
  isShow?: boolean | null;
  score?: number | null;
  isFirst?: boolean | null;
  comment?: string | null;
}

export const defaultValue: Readonly<IRelCategoryCity> = {
  isShow: false,
  isFirst: false,
};
