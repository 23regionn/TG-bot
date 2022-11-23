import dayjs from 'dayjs';

export interface IConfigTable {
  id?: number;
  dateOne?: string | null;
  dateTwo?: string | null;
  longOne?: number | null;
  stringOne?: string | null;
  booleanOne?: boolean | null;
  booleanTwo?: boolean | null;
  date1?: string | null;
  date2?: string | null;
  long1?: number | null;
  string1?: string | null;
  boolean1?: boolean | null;
}

export const defaultValue: Readonly<IConfigTable> = {
  booleanOne: false,
  booleanTwo: false,
  boolean1: false,
};
