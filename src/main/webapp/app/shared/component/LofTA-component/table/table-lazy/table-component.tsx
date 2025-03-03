import React, { useEffect, useRef, useState } from 'react';
import { Button } from 'primereact/button';
import { Column } from 'primereact/column';
import { DataTable } from 'primereact/datatable';

interface InitializationParams {
  match: any;
  getEntities(dataFormat): any;
  column: any;
  columnBody: any;
  loading: any;
  arrayCount?: any;
  lazyParamsFilter?: any;
  onFilter?: any;
  filterJson?: any;
  frozenValue?: boolean;
}

export const TableComponent = (props: InitializationParams) => {
  const [items, setItems] = useState(null);
  const [lockedItems, setLockedItems] = useState([]);
  const [totalRecords, setTotalRecords] = useState(0);
  const [rows, setRows] = useState(5);
  const [lazyParamsPage, setLazyParamsPage] = useState({
    first: 0,
    page: 0,
  });
  const [lazyParamsSort, setLazyParamsSort] = useState({
    sortField: null,
    sortOrder: null,
  });

  const paginatorJsonForm = { numberPage: lazyParamsPage.page, countElement: rows }; // Содержит баззовую информацию для отправки на сервер
  const sortsJsonForm = { columnName: lazyParamsSort.sortField, optionalSort: lazyParamsSort.sortOrder };

  const FormatData = {
    id: props.match.params.idS,
    paginatorJson: paginatorJsonForm,
    sortsJson: sortsJsonForm,
    filterJsonForm: props.filterJson ? props.filterJson : null,
  };

  const getItemsRequest = () => {
    props.getEntities(FormatData).then(result => {
      setItems(result.value.data.content);
      setTotalRecords(result.value.data.totalElements);
    });
  };

  useEffect(() => {
    getItemsRequest();
  }, [props.match.params.idS]);

  useEffect(() => {
    getItemsRequest();
  }, [lazyParamsPage.page, lazyParamsSort.sortField, lazyParamsSort.sortOrder, props.lazyParamsFilter.filters, rows]);

  const onPage = event => {
    setRows(event.rows);
    setLazyParamsPage(event);
  };

  const onSort = event => {
    setLazyParamsSort(event);
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
        filterPlaceholder={col.filterPlaceholder}
        filterMatchMode={col.filterMatchMode}
        showFilterMenuOptions={col.showFilterMenuOptions}
        showFilterMenu={col.showFilterMenu}
        showClearButton={col.showClearButton}
        style={col.style}
      />
    );
  });

  const dynamicColumnsBody = props.columnBody.map((col, i) => {
    return <Column key={i} body={col.body} style={col.style} />;
  });

  const lockTemplate = (rowData, options) => {
    const iconValue = options.frozenRow ? 'pi pi-lock-open' : 'pi pi-lock';
    const disabled = options.frozenRow ? false : lockedItems.length >= 2;

    return (
      <Button
        type="button"
        icon={iconValue}
        disabled={disabled}
        className="p-button-sm p-button-text"
        onClick={() => toggleLock(rowData, options.frozenRow)}
      />
    );
  };

  const toggleLock = (data, frozen) => {
    let _lockedItems, _items;

    if (frozen) {
      _lockedItems = lockedItems.filter(item => item.id !== data.id);
    } else {
      _lockedItems = [...lockedItems, data];
    }

    setLockedItems(_lockedItems);
  };

  return (
    <DataTable
      dataKey="id"
      value={
        lockedItems.length > 0
          ? items.reduce((result, item) => {
              if (lockedItems.find(lockedItem => lockedItem.id === item.id)) {
                return result;
              } else {
                result.push(item);
                return result;
              }
            }, [])
          : items
      }
      responsiveLayout="scroll"
      scrollable
      scrollHeight="600px"
      frozenValue={lockedItems}
      showGridlines={true}
      emptyMessage="Нет данных"
      lazy
      paginator={true}
      first={lazyParamsPage.first}
      rows={rows}
      totalRecords={totalRecords}
      onPage={onPage}
      onSort={onSort}
      sortField={lazyParamsSort.sortField}
      sortOrder={lazyParamsSort.sortOrder}
      filterDisplay="row"
      filters={props.lazyParamsFilter.filters}
      onFilter={props.onFilter}
      loading={props.loading}
      paginatorTemplate="CurrentPageReport FirstPageLink PrevPageLink PageLinks NextPageLink LastPageLink RowsPerPageDropdown"
      currentPageReportTemplate="Показано с {first} по {last} всего {totalRecords}"
      rowsPerPageOptions={props.arrayCount ? props.arrayCount : false}
    >
      {dynamicColumns}
      {dynamicColumnsBody}
      {props.frozenValue ? <Column style={{ maxWidth: '4%' }} body={lockTemplate}></Column> : null}
    </DataTable>
  );
};
