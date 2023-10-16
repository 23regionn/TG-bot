import { ICategory } from 'app/shared/model/category.model';
import { IChanell } from 'app/shared/model/chanell.model';

export interface IRelCategoryChannels {
  id?: number;
  scoreChannel?: number | null;
  isShowChannel?: boolean | null;
  category?: ICategory[] | null;
  chanell?: IChanell[] | null;
  comment?: string | null;
}

export const defaultValue: Readonly<IRelCategoryChannels> = {
  isShowChannel: false,
};
