import { Column } from 'primereact/column';
import React from 'react';

export const DynamicColumns = column => {
  return column.map((col, i) => {
    return col.check !== null && col.check !== undefined ? (
      col.check ? (
        <Column
          key={i}
          header={col.header ? col.header : null}
          field={col.field}
          sortField={col.sortField ? col.sortField : col.field}
          expander={col.expander ? col.expander : col.expander}
          filterField={col.filterField ? col.filterField : col.field}
          headerStyle={col.headerStyle ? col.headerStyle : null}
          bodyStyle={col.bodyStyle ? col.bodyStyle : null}
          style={col.style ? col.style : null}
          sortable={col.sortable ? col.sortable : false}
          filter={col.filter ? col.filter : null}
          showFilterMatchModes={col.showFilterMatchModes ? col.showFilterMatchModes : false}
          showAddButton={col.showAddButton ? col.showAddButton : false}
          filterMatchMode={col.filterMatchMode ? col.filterMatchMode : 'contains'}
          dataType={col.dataType ? col.dataType : 'date'}
          filterElement={col.filterElement ? col.filterElement : false}
          filterFunction={col.filterFunction ? col.filterFunction : false}
          body={col.body ? col.body : null}
        />
      ) : null
    ) : (
      <Column
        key={i}
        header={col.header ? col.header : null}
        field={col.field}
        sortField={col.sortField ? col.sortField : null}
        expander={col.expander ? col.expander : col.expander}
        filterField={col.filterField ? col.filterField : null}
        headerStyle={col.headerStyle ? col.headerStyle : null}
        bodyStyle={col.bodyStyle ? col.bodyStyle : null}
        style={col.style ? col.style : null}
        sortable={col.sortable ? col.sortable : false}
        filter={col.filter ? col.filter : null}
        showFilterMatchModes={col.showFilterMatchModes ? col.showFilterMatchModes : false}
        showAddButton={col.showAddButton ? col.showAddButton : false}
        filterMatchMode={col.filterMatchMode ? col.filterMatchMode : 'contains'}
        dataType={col.dataType ? col.dataType : 'date'}
        filterElement={col.filterElement ? col.filterElement : null}
        filterFunction={col.filterFunction ? col.filterFunction : null}
        filterApply={col.filterApply ? col.filterApply : null}
        body={col.body ? col.body : null}
      />
    );
  });
};
