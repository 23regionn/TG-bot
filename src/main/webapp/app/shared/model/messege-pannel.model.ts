import dayjs from 'dayjs';

export interface IMessegePannel {
  id?: number;
  idMessage?: number | null;
  idChannel?: number | null;
  dateCreateMessage?: string | null;
  textMessage?: string | null;
  idAdmin?: number | null;
  comment?: string | null;
  status?: string | null;
  serviceField1?: string | null;
  serviceField2?: string | null;
  serviceField3?: string | null;
}

export const defaultValue: Readonly<IMessegePannel> = {};
