import './Button_basic.scss';
import { Button } from 'primereact/button';

import React from 'react';

export interface IButton_basicProps {
  label?: string;
  onClick: (e: any) => void;
  disabled?: boolean;
  loading?: boolean;
  className?: string;
}
const Button_basic = (props: IButton_basicProps) => {
  return <Button id="button_basic" label={props.label} onClick={props.onClick} disabled={props.disabled} />;
};

export default Button_basic;
