import '../Button_basic/Button_basic.scss';
import '../Button_change/Button_change.scss';
import '../Button_cancel/Button_cancel.scss';
import '../Button_delete/Button_delete.scss';
import '../Button_save/Button_save.scss';
import '../Button_filter/button-filter.scss';
import './buttons-actions.scss';
import React, { useEffect, useState } from 'react';
import { Button } from 'primereact/button';

export interface ButtonProps {
  id: any;
  label?: string;
  onClick?: (e: any) => void;
  disabled?: boolean;
  inactive?: boolean;
}

export interface IButtonFooterProps {
  firstButton?: ButtonProps;
  secondButton?: ButtonProps;
  thirdsButton?: ButtonProps;
}

export const ButtonsActions = (props: IButtonFooterProps) => {
  const [classNameFirstBlock, setClassNameFirstBlock] = useState('first-block');
  const [classNameSecondBlock, setClassNameSecondBlock] = useState('second-block');
  const [classNameThirdsBlock, setClassNameThirdsBlock] = useState('thirds-block');

  const arrayScssButtons = [
    { id: 0, style: 'button_basic' },
    { id: 1, style: 'button_change' },
    { id: 2, style: 'button_cancel' },
    { id: 3, style: 'button_delete' },
    { id: 4, style: 'button_save' },
    { id: 5, style: 'button-filter' },
  ];

  useEffect(() => {
    generateClassName();
  }, []);
  const generateClassName = () => {
    props.firstButton
      ? props.secondButton
        ? props.thirdsButton
          ? generateSecondClass(1)
          : generateSecondClass(2)
        : props.thirdsButton
        ? generateFirstClass(1)
        : generateFirstClass(0)
      : props.secondButton
      ? props.thirdsButton
        ? generateSecondClass(3)
        : generateSecondClass(0)
      : props.thirdsButton
      ? generateThirdsClass(0)
      : null;
  };
  const generateFirstClass = options => {
    switch (options) {
      case 0: {
        setClassNameFirstBlock('first-block');
        break;
      }
      case 1: {
        setClassNameFirstBlock('first-block only-thirds-block');
        break;
      }

      default:
        setClassNameFirstBlock('first-block');
    }
  };
  const generateSecondClass = options => {
    switch (options) {
      case 0: {
        setClassNameSecondBlock('second-block');
        break;
      }
      case 1: {
        setClassNameSecondBlock('second-block all-blocks');
        break;
      }
      case 2: {
        setClassNameSecondBlock('second-block only-first-block');
        break;
      }
      case 3: {
        setClassNameSecondBlock('second-block only-thirds-block');
        break;
      }

      default:
        setClassNameSecondBlock('second-block');
    }
  };
  const generateThirdsClass = options => {
    switch (options) {
      case 0: {
        setClassNameThirdsBlock('thirds-block');
        break;
      }

      default:
        setClassNameThirdsBlock('thirds-block');
    }
  };

  const button = (classBlock, buttonProps) => {
    return (
      <div className={classBlock}>
        {buttonProps.inactive ? (
          !buttonProps.inactive && (
            <Button
              id={arrayScssButtons[buttonProps.id].style}
              onClick={buttonProps.onClick}
              label={buttonProps.label ? buttonProps.label : ''}
              disabled={buttonProps?.disabled ? buttonProps.disabled : false}
            />
          )
        ) : (
          <Button
            id={arrayScssButtons[buttonProps.id].style}
            onClick={buttonProps.onClick}
            label={buttonProps.label ? buttonProps.label : ''}
            disabled={buttonProps?.disabled ? buttonProps.disabled : false}
          />
        )}
      </div>
    );
  };

  return (
    <div className="button-block">
      {props.firstButton ? button(classNameFirstBlock, props.firstButton) : null}
      {props.secondButton ? button(classNameSecondBlock, props.secondButton) : null}
      {props.thirdsButton ? button(classNameThirdsBlock, props.thirdsButton) : null}
    </div>
  );
};
