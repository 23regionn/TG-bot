import './Button_save.scss';
import { Button } from 'primereact/button';

import React from 'react';

export interface IButton_saveProps {
  className?: string;
  label: string;
  onClick?: () => void;
  disabled?: boolean;
  loading?: boolean;
}
const Button_save = (props: IButton_saveProps) => {
  return <Button id="button_save" label={props.label} onClick={props.onClick} disabled={props.disabled} />;
};

export default Button_save;
