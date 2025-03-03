import React from 'react';
import 'primeflex/primeflex.css';
import { InputText } from 'primereact/inputtext';

export interface InputTextHelperComponentProps {
  id: any;
  className: any;
  defaultValue: any;
  onChange: any;
  error?: string;
  label?: string;
}

export const InputTextHelperComponent = (props: InputTextHelperComponentProps) => {
  const error = 'Поле не заполнено';

  const labelInputText = label => {
    return (
      <label>
        <span className="font-weight-bold" id="text-size">
          {label}
        </span>
      </label>
    );
  };

  const inputText = () => {
    return (
      <div>
        <InputText
          id={props.id}
          key={props.id}
          aria-describedby="name-help"
          className={`${props.className} inputs_text`}
          defaultValue={props.defaultValue}
          onChange={props.onChange}
        ></InputText>
        {props.className === '' ? null : (
          <small id={props.id + '-help'} className="text-center p-error block">
            {props.error ? props.error : error}
          </small>
        )}
      </div>
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

  return props.label ? inputTextWhithLabel() : inputText();
};

export default InputTextHelperComponent;
