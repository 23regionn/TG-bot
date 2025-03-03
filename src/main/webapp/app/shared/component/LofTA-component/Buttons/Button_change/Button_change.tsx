import './Button_change.scss';
import { Button } from 'primereact/button';

import React from 'react';

export interface IButton_changeProps {
  id?: string;
  label?: string;
  onClick?: (e: any) => void;
  className?: string;
  disabled?: boolean;
  icon?: any;
  loading?: boolean;
}
const Button_change = (props: IButton_changeProps) => {
  return <Button id="button_change" label={props.label} onClick={props.onClick} disabled={props.disabled} />;
};

export default Button_change;
