import './split-button.scss';
import React, { useEffect, useRef, useState } from 'react';
import { SplitButton } from 'primereact/splitbutton';

export interface ISplitButtonProps {
  itemsMenu: any;
  icon?: any;
}

export const SplitSingleButton = (props: ISplitButtonProps) => {
  const iconDefault = 'pi pi-ellipsis-v';

  // Пример структуры, засылается полная структура,
  // даже если некоторые элементы могут не отображаться в определённые моменты
  /*  const Simple =[
    {
      label: 'Название',
      icon: 'иконка',
      command: () => props.onDelete(props.rowData),
    }
  ]*/

  return <SplitButton id="singleSplitButton" dropdownIcon={props.icon ? props.icon : iconDefault} model={props.itemsMenu}></SplitButton>;
};
