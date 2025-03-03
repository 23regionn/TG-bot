import '../table-component.scss';
import { Column } from 'primereact/column';
import React, { useState } from 'react';
import { CalendarComponent } from 'app/shared/component/LofTA-component/сalendar-component/calendar-component';
import { DataTable } from 'primereact/datatable';
import { LoaderComponent } from 'app/shared/component/LofTA-component/loader/loader-component';
import { Button } from 'primereact/button';
import { DynamicColumns } from 'app/shared/component/LofTA-component/constant/constant';

interface InitializationParams {
  tableParam: any;
  item: any;
  column: any;
  filter: any;
  frozenValue?: any;
  loading?: boolean | false;
  customLoading?: boolean | false;
}

export const TableComponentNoLazy = (props: InitializationParams) => {
  const [lockedItems, setLockedItems] = useState([]);
  const lockTemplate = (rowData, options) => {
    const iconValue = options.frozenRow ? 'pi pi-lock-open' : 'pi pi-lock';
    const disabled = options.frozenRow ? false : lockedItems.length >= 2;
    const classNameClose = 'p-button-rounded p-button-danger p-button-text style-icon-block';
    const classNameOpen = 'p-button-rounded p-button-success p-button-text style-icon-block';
    const classNameIcon = options.frozenRow ? classNameOpen : classNameClose;

    return <Button icon={iconValue} disabled={disabled} className={classNameIcon} onClick={() => toggleLock(rowData, options.frozenRow)} />;
  };
  const toggleLock = (data, frozen) => {
    let _lockedItems;

    if (frozen) {
      _lockedItems = lockedItems.filter(item => item.id !== data.id);
    } else {
      _lockedItems = [...lockedItems, data];
    }

    setLockedItems(_lockedItems);
  };
  return (
    <div className="div-style">
      {props.customLoading ? <LoaderComponent /> : <></>}
      <DataTable
        ref={props.tableParam.ref ? props.tableParam.ref : null}
        dataKey={props.tableParam.dataKey ? props.tableParam.dataKey : null}
        className="tableStyle1"
        value={
          lockedItems.length > 0
            ? props.item.reduce((result, item) => {
                if (lockedItems.find(lockedItem => lockedItem.id === item.id)) {
                  return result;
                } else {
                  result.push(item);
                  return result;
                }
              }, [])
            : props.item
        }
        removableSort={props.tableParam.removableSort ? props.tableParam.removableSort : true}
        filterDisplay={props.filter.filterDisplay ? props.filter.filterDisplay : 'menu'}
        filters={props.filter.filters ? props.filter.filters : null}
        paginator={props.tableParam.rows ? props.item.length > props.tableParam.rows : false}
        rows={props.tableParam.rows ? props.tableParam.rows : null}
        first={props.tableParam.first ? props.tableParam.first : 0}
        totalRecords={props.item.length}
        autoLayout
        showGridlines={true}
        paginatorTemplate="CurrentPageReport FirstPageLink PrevPageLink PageLinks NextPageLink LastPageLink RowsPerPageDropdown"
        currentPageReportTemplate="Показано с {first} по {last} всего {totalRecords}"
        frozenValue={lockedItems}
        filterLocale="ru"
        loading={props.loading ? props.loading : null}
      >
        {props.frozenValue ? <Column body={lockTemplate}></Column> : null}
        {DynamicColumns(props.column)}
      </DataTable>
    </div>
  );
};
