import React, { useEffect, useState } from 'react';
import 'primeflex/primeflex.css';
import { Calendar } from 'primereact/calendar';
import './date-component.scss';
export interface IInitial {
  id: string;
  label?: string;
  value: any;
  onChange: any;
  errorEmpty?: boolean | false;
  placeholder?: string;
  selectionMode?: string;
}

export const DateComponent = (props: IInitial) => {
  const invalidBlock = 'p-invalid block';
  const block = 'block';
  const [flagError, setFlagError] = useState(false);

  const labelInputText = label => {
    return (
      <label>
        <span className="font-weight-bold" id="text-size">
          {label}
        </span>
      </label>
    );
  };

  const inputDateWhithLabel = () => {
    return (
      <div>
        {labelInputText(props.label)}
        {inputDate()}
      </div>
    );
  };

  const updateDateValue = e => {
    e.target.value === '' ? setFlagError(true) : setFlagError(false);
  };

  useEffect(() => {
    props.value === '' ? setFlagError(true) : setFlagError(false);
    const calendarElement = document.getElementsByClassName('p-datepicker')[0];
    if (props?.value?.length && props?.value[1] !== null) calendarElement && calendarElement.classList.add('d_none');
  }, [props.value]);

  const inputDate = () => {
    return (
      <Calendar
        // locale={'ru'}
        monthNavigator
        yearNavigator
        yearRange={'1990:' + new Date().getFullYear()}
        dateFormat={'dd.mm.yy'}
        mask={'99.99.9999'}
        value={props.value}
        placeholder={props.placeholder ? props.placeholder : ''}
        onChange={props.onChange}
        className={props.errorEmpty ? (flagError ? invalidBlock : block) : block}
        inputClassName="inputs_text"
        showButtonBar
        selectionMode={props.selectionMode ? props.selectionMode : null}
        readOnlyInput
      />
    );
  };

  return props.label ? inputDateWhithLabel() : inputDate();
};

export default DateComponent;
