import React from 'react';
import 'primeflex/primeflex.css';
import { Dropdown } from 'primereact/dropdown';
import classNames from 'classnames';

export interface DropdownComponentProps {
  id?: any;
  className?: string;
  label?: any;
  value?: string;
  onChange: any;
  options: any;
  valueForForm: string;
  placeholder: string;
  filter?: boolean;
  editable?: boolean;
  showClear?: boolean;
  disabled?: boolean | false;
  virtualScroll?: boolean | false;
  valueTemplate?: any | null;
  itemTemplate?: any | null;
}

export const DropdownComponent = (props: DropdownComponentProps) => {
  const labelDropdown = label => {
    return (
      <label>
        <span className="font-weight-bold" id="text-size">
          {label}
        </span>
      </label>
    );
  };

  const dropDownWithLabel = () => {
    return (
      <div>
        {labelDropdown(props.label)}
        {dropDown()}
      </div>
    );
  };

  const dropDown = () => {
    return (
      <Dropdown
        id={props.id}
        className={classNames('inputs_dropdown', props.className)}
        value={props.value}
        onChange={props.onChange}
        options={props.options}
        optionLabel={props.valueForForm}
        optionValue={props.valueTemplate ? null : props.valueForForm}
        valueTemplate={props.valueTemplate}
        itemTemplate={props.itemTemplate}
        filterBy={props.valueForForm}
        filter={props.filter ? props.filter : false}
        editable={props.editable ? props.editable : false}
        showClear={props.showClear !== undefined ? props.showClear : true}
        placeholder={props.placeholder}
        disabled={props.disabled}
        virtualScrollerOptions={props.virtualScroll ? { itemSize: 38 } : null}
        emptyMessage="Нет данных"
        emptyFilterMessage="Нет данных"
      />
    );
  };

  return props.label ? dropDownWithLabel() : dropDown();
};

export default DropdownComponent;
