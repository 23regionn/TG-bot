import './content-table-format.scss';
import React, { useEffect } from 'react';
import { TableComponentNew } from 'app/shared/component/LofTA-component/table/table-lazy/table-component-new';
import { LoaderComponent } from 'app/shared/component/LofTA-component/loader/loader-component';
import Button_basic from '../../Buttons/Button_basic/Button_basic';

export interface ContentProps {
  item: any;
  getItem: any;
  column: any;
  columnBody: any;
  rows: any;
  totalRecords?: any;
  lazyParamsFilter?: any;
  onFilter?: any;
  title?: any;
  nameButton?: any;
  onCreate?(): void;
  arrayCount?: any;
  frozenValue?: any;
  customUpdateUrl?: any;
  loading?: any;
  loadingData?: boolean | false;
}

export const ContentTableFormat = (props: ContentProps) => {
  return (
    <div className="content-table-format">
      {props.loadingData ? <LoaderComponent /> : <></>}
      <div className="table-format">
        <div className="table-format-header">{props.title}</div>
        {props.nameButton ? (
          <div className="table-format-button-block ">
            <Button_basic label={props.nameButton} onClick={props.onCreate} />
          </div>
        ) : null}
        <div className="table-format-block height_69">
          <TableComponentNew
            item={props.item}
            getItem={props.getItem}
            column={props.column}
            columnBody={props.columnBody}
            lazyParamsFilter={props.lazyParamsFilter}
            onFilter={props.onFilter}
            rows={5}
            frozenValue={props.frozenValue ? props.frozenValue : false}
            arrayCount={[5, 10, 15]}
            customUpdateUrl={props.customUpdateUrl}
            loading={props.loading}
          />
        </div>
      </div>
    </div>
  );
};
