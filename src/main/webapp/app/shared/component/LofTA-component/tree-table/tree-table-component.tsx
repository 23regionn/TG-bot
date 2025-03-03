import './tree-table-component.scss';
import React, { useState } from 'react';
import { Column } from 'primereact/column';
import { TreeTable } from 'primereact/treetable';
import { ButtonFooter } from 'app/shared/component/LofTA-component/button-footer/botten-footer';
import Button_change from '../Buttons/Button_change/Button_change';

interface InitializationParams {
  items: any;
  footer?: any;
  columnBody?: any;
  column: any;
  lazy?: any;
  onSelect?: any;
}

export const TreeTableComponent = (props: InitializationParams) => {
  const [selectedNodeKey, setSelectedNodeKey] = useState(null);

  const dynamicColumns = props.column.map((col, i) => {
    return (
      <Column
        key={i}
        field={col.field}
        header={col.header}
        expander={col.expander}
        sortField={col.sortField}
        sortable={col.sortable}
        filter={!props.lazy ? col.filter : false}
        filterPlaceholder={col.filterPlaceholder}
        filterMatchMode={col.filterMatchMode}
        style={col.style}
        headerStyle={col.styleHeader}
      />
    );
  });
  const dynamicColumnsBody = props.columnBody.map((col, i) => {
    return <Column key={i} body={col.body} style={col.style} headerStyle={col.styleHeader} />;
  });

  const onExpand = event => {};

  return (
    <TreeTable
      id="treeTableStyle1"
      value={props.items}
      scrollable
      scrollHeight="100%"
      filterMode="strict"
      showGridlines={true}
      emptyMessage="Нет данных"
      footer={
        <div className="d-flex justify-content-end">
          <Button_change label={'Назад'} onClick={() => props.footer()} />
        </div>
        // props.footer ? <ButtonFooter firstButton={props.footer.footerButtonFirst} thirdsButton={props.footer.footerButtonThirds} /> : null
      }
      onExpand={!props.lazy ? onExpand : props.lazy?.onExpand}
      selectionMode={!props.onSelect ? null : props.onSelect.selectionMods ? props.onSelect.selectionMods : 'single'}
      selectionKeys={!props.onSelect ? null : selectedNodeKey}
      onSelectionChange={!props.onSelect ? null : e => setSelectedNodeKey(e.value)}
      metaKeySelection={!props.onSelect ? null : props.onSelect.metaKeySelection ? props.onSelect.metaKeySelection : null}
      onSelect={!props.onSelect ? null : props.onSelect.onSelect ? props.onSelect.onSelect : null}
      onUnselect={!props.onSelect ? null : props.onSelect.onUnselect ? props.onSelect.onUnselect : null}
      lazy={!props.lazy ? false : true}
      paginator={!props.lazy?.paginator ? false : true}
    >
      {dynamicColumns}
      {props.columnBody ? dynamicColumnsBody : null}
    </TreeTable>
  );
};
