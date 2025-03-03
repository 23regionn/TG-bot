import React, { useEffect, useState } from 'react';
import 'primeflex/primeflex.css';
import { InputText } from 'primereact/inputtext';
import './input-text-component.scss';
export interface InputTextComponentProps {
  id: string;
  label?: string;
  value: string;
  onChange: any;
  errorEmpty?: boolean | false;
  placeholder?: string;
  keyFilter?: any;
}

export const InputTextComponent = (props: InputTextComponentProps) => {
  const invalidBlock = 'p-invalid block inputs_text';
  const block = 'block inputs_text';
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

  const inputTextWhithLabel = () => {
    return (
      <div>
        {labelInputText(props.label)}
        {inputText()}
      </div>
    );
  };

  const updateInputValue = e => {
    e.target.value === '' ? setFlagError(true) : setFlagError(false);
  };

  useEffect(() => {
    props.value === '' ? setFlagError(true) : setFlagError(false);
  }, [props.value]);

  const inputText = () => {
    return (
      <InputText
        id={props.id}
        value={props.value}
        onChange={props.onChange}
        onInput={e => updateInputValue(e)}
        className={props.errorEmpty ? (flagError ? invalidBlock : block) : block}
        placeholder={props.placeholder ? props.placeholder : ''}
        keyfilter={props.keyFilter ? props.keyFilter : null}
      ></InputText>
    );
  };

  return props.label ? inputTextWhithLabel() : inputText();
};

export default InputTextComponent;
