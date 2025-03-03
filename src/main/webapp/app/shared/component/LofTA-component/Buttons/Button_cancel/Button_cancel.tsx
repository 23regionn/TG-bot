import './Button_cancel.scss';
import { Button } from 'primereact/button';

import React from 'react';

export interface IButton_cancelProps {
  label: string;
  onClick?: () => void;
  disabled?: boolean;
  className?: string;
}
const Button_cancel = (props: IButton_cancelProps) => {
  return <Button id="button_cancel" label={props.label} onClick={props.onClick} disabled={props.disabled} />;
};

export default Button_cancel;
