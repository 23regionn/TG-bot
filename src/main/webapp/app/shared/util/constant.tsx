import { ISort } from 'app/shared/util/sort-constants';
import { IPaginator } from 'app/shared/util/pagination.constants';

export interface IPaginatorCustom {
  sort: ISort;
  paginator: IPaginator;
  filter: any;
}

export interface IFilter {
  /*inspectionInvIdName: string;
  entityHierarchyName: string;*/
  name: string;
  link: string;
  lastPayDate: string;
  endPublicDate: string;
}
