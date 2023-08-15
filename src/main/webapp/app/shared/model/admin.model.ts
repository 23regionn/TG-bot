export interface IAdmin {
  id?: number;
  name?: string | null;
  contact?: string | null;
  link?: string | null;
  isActive?: boolean | null;
}

export const defaultValue: Readonly<IAdmin> = {
  isActive: false,
};
