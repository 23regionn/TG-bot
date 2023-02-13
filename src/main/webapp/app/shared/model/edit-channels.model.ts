import dayjs from 'dayjs';

export interface IEditChannels {
  id?: number;
  idMessage?: number | null;
  idChannel?: number | null;
  dateCreateMessage?: string | null;
  lastNameChannel?: string | null;
  newNameChannel?: string | null;
  isAproveChange?: string | null;
  lastLinkToChannel?: string | null;
  newlastLinkToChannel?: string | null;
  lastPriceChannel?: number | null;
  newPriceChannel?: number | null;
  addDescriptionAboutChannel?: string | null;
  currentDescriptionChannel?: string | null;
  addRegionChannel?: string | null;
  editRegionChannel?: string | null;
  addCityChannel?: string | null;
  editCityChannel?: string | null;
  userId?: number | null;
  userName?: string | null;
  isApprovedChanhes?: boolean | null;
  comment?: string | null;
  status?: string | null;
  serviceField1?: string | null;
  serviceField2?: string | null;
  serviceField3?: string | null;
}

export const defaultValue: Readonly<IEditChannels> = {
  isApprovedChanhes: false,
};
