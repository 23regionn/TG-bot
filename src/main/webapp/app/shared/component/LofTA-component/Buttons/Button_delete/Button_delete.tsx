import './Button_delete.scss';
import { Button } from 'primereact/button';

import React from 'react';

export interface IButton_deleteProps {
  label?: string;
  onClick: () => void;
  loading?: boolean;
  disabled?: boolean;
  spin?: boolean;
  icon?: any;
}
const Button_delete = (props: IButton_deleteProps) => {
  return <Button id="button_delete" label={props.label} onClick={props.onClick} disabled={props.disabled} />;
};

export default Button_delete;
