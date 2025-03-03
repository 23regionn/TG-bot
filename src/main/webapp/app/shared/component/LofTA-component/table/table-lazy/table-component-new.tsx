import '../table-component.scss';
import React, { useEffect, useState } from 'react';
import { Column } from 'primereact/column';
import { DataTable } from 'primereact/datatable';
import { Button } from 'primereact/button';
import { CalendarComponent } from 'app/shared/component/LofTA-component/сalendar-component/calendar-component';
import { LoaderComponent } from 'app/shared/component/LofTA-component/loader/loader-component';
import { translate } from 'react-jhipster';
import { IPaginator } from 'app/shared/util/pagination.constants';
import { ISort } from 'app/shared/util/sort-constants';

interface InitializationParams {
  item: any;
  getItem: any;
  column: any;
  columnBody: any;
  rows: any;
  lazyParamsFilter?: any;
  onFilter?: any;
  arrayCount?: any;
  frozenValue?: any;
  customUpdateUrl?: any | false;
  loading?: boolean | false;
  customLoading?: boolean | false;
}

export const TableComponentNew = (props: InitializationParams) => {
  const [flagCustomResetSuccessful, setFlagCustomResetSuccessful] = useState(false);
  const [flagFilter, setFlagFilter] = useState(false);
  const [lazyParamsPage, setLazyParamsPage] = useState({
    first: 0,
    rows: props.rows ? props.rows : 5,
    page: 0,
  });
  const [lazyParamsPageDefault] = useState({
    first: 0,
    rows: props.rows ? props.rows : 5,
    page: 0,
  });
  const [lazyParamsSort, setLazyParamsSort] = useState({
    sortField: null,
    sortOrder: null,
  });
  const [lazyParamsSortDefault] = useState({
    sortField: null,
    sortOrder: null,
  });

  const paginatorJsonForm: IPaginator = { numberPage: lazyParamsPage.page, countElement: lazyParamsPage.rows };
  const sortsJsonForm: ISort = { sortField: lazyParamsSort.sortField, sortOrder: lazyParamsSort.sortOrder };

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

  const dateFilterTemplate = options => {
    return <CalendarComponent options={options} onSelect={options.filterApplyCallback} />;
  };

  const dynamicColumns = props.column.map((col, i) => {
    return (
      <Column
        key={i}
        field={col.field}
        header={col.header}
        sortField={col.sortField}
        sortable={col.sortable}
        filter={col.filter}
        filterPlaceholder={col.filterPlaceholder ? col.filterPlaceholder : null}
        filterMatchMode={col.filterMatchMode ? col.filterMatchMode : 'dateIs'}
        dataType={col.useDate ? 'date' : null}
        filterElement={col.useDate ? dateFilterTemplate : false}
        showFilterMenuOptions={col.showFilterMenuOptions}
        showFilterMenu={col.showFilterMenu}
        showClearButton={col.useDate ? true : col.showClearButton}
        style={col.style ? col.style : null}
        className={col.className ? `${col.className} filter_table_tech_acceptance` : 'filter_table_tech_acceptance'}
        body={rowData => {
          if (typeof rowData[col.field] === 'boolean') {
            return <span>{rowData[col.field] ? 'Да' : 'Нет'}</span>;
          } else {
            return <span>{rowData[col.field]}</span>;
          }
        }}
      />
    );
  });

  const dynamicColumnsBody = props.columnBody.map((col, i) => {
    return <Column key={i} body={col.body} style={col.style ? col.style : null} className={col.className ? col.className : null} />;
  });

  const onPage = event => {
    setLazyParamsPage(event);
  };

  const onSort = event => {
    setLazyParamsSort(event);
  };

  const resetValue = () => {
    setLockedItems([]);
    if (lazyParamsPage === lazyParamsPageDefault) {
      if (lazyParamsSort === lazyParamsSortDefault) {
        setFlagCustomResetSuccessful(!flagCustomResetSuccessful);
      } else {
        setLazyParamsSort(lazyParamsSortDefault);
      }
    } else {
      if (lazyParamsSort !== lazyParamsSortDefault) {
        setLazyParamsSort(lazyParamsSortDefault);
      }
      setLazyParamsPage(lazyParamsPageDefault);
    }
  };

  const onFilter = event => {
    props.onFilter(event);
    setFlagFilter(!flagFilter);
  };

  useEffect(() => {
    props.getItem(paginatorJsonForm, sortsJsonForm);
  }, [lazyParamsPage.page, lazyParamsSort.sortField, lazyParamsSort.sortOrder, lazyParamsPage.rows, flagFilter, flagCustomResetSuccessful]);

  {
    props.customUpdateUrl
      ? useEffect(() => {
          if (props.customUpdateUrl !== false) {
            resetValue();
          }
        }, [props.customUpdateUrl])
      : null;
  }
  return (
    <div className="div-style">
      {props.customLoading ? <LoaderComponent /> : <></>}
      <DataTable
        dataKey="id"
        className="tableStyle1"
        value={
          lockedItems.length > 0
            ? props.item.content.reduce((result, item) => {
                if (lockedItems.find(lockedItem => lockedItem.id === item.id)) {
                  return result;
                } else {
                  result.push(item);
                  return result;
                }
              }, [])
            : props.item.content
        }
        responsiveLayout="scroll"
        scrollable
        scrollHeight="flex"
        showGridlines={true}
        emptyMessage={translate('global.select.nodata')}
        lazy
        paginator={true}
        frozenValue={lockedItems}
        first={lazyParamsPage.first}
        rows={lazyParamsPage.rows}
        totalRecords={props.item.totalElements}
        onPage={onPage}
        onSort={onSort}
        sortField={lazyParamsSort.sortField}
        sortOrder={lazyParamsSort.sortOrder}
        filterDisplay="row"
        filters={props.lazyParamsFilter ? props.lazyParamsFilter.filters : false}
        onFilter={onFilter}
        paginatorTemplate="CurrentPageReport FirstPageLink PrevPageLink PageLinks NextPageLink LastPageLink RowsPerPageDropdown"
        currentPageReportTemplate="Показано с {first} по {last} всего {totalRecords}"
        rowsPerPageOptions={props.arrayCount ? props.arrayCount : false}
        loading={props.loading}
      >
        {props.frozenValue ? <Column style={{ maxWidth: '4%', minWidth: '4%' }} body={lockTemplate}></Column> : null}
        {dynamicColumns}
        {dynamicColumnsBody}
      </DataTable>
    </div>
  );
};
