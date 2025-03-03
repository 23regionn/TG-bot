import React, { useEffect, useState } from 'react';
import 'primeflex/primeflex.css';
import { Dropdown } from 'primereact/dropdown';
import { Translate } from 'react-jhipster';
import { InputText } from 'primereact/inputtext';
import { Button } from 'primereact/button';

export interface InputGroupComponentProps {
  id?: string;
  componentFirst: any;
  // componentSecond: any;
  componentSecond?: any;
  componentThird?: any;
  label?: any;
  value?: string;
  onChange?: any;
}

export const InputGroupComponent = (props: InputGroupComponentProps) => {
  const labelInputGroup = label => {
    return (
      <label>
        <span className="font-weight-bold" id="text-size">
          {label}
        </span>
      </label>
    );
  };

  const inputGroupWhithLabel = () => {
    return (
      <div>
        {labelInputGroup(props.label)}
        {inputGroup()}
      </div>
    );
  };

  const inputGroup = () => {
    return (
      <div className="">
        <div id="inputs_text" className="p-inputgroup">
          {props.componentFirst}
          {/* {props.componentSecond} */}
          {props.componentThird ? props.componentThird : null}
        </div>
      </div>
    );
  };

  return props.label ? inputGroupWhithLabel() : inputGroup();
};

export default InputGroupComponent;
