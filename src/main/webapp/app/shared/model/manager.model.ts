export interface IManager {
  id?: number;
  name?: string | null;
  contact?: string | null;
}

export const defaultValue: Readonly<IManager> = {};
