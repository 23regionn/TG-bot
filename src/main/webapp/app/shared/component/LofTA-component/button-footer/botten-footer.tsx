import './button-footer.scss';
import React, { useEffect, useState } from 'react';
import { Button } from 'primereact/button';

export interface IButtonFooterProps {
  firstButton?: any;
  secondButton?: any;
  thirdsButton?: any;
}

export const ButtonFooter = (props: IButtonFooterProps) => {
  const [classNameFirstBlock, setClassNameFirstBlock] = useState('first-block');
  const [classNameSecondBlock, setClassNameSecondBlock] = useState('second-block');
  const [classNameThirdsBlock, setClassNameThirdsBlock] = useState('thirds-block');

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

  const firstBlock = () => {
    return (
      <div className={classNameFirstBlock}>
        <Button
          className="firstButton"
          onClick={() => {
            props.firstButton.onClick();
          }}
          disabled={props.firstButton?.block ? props.firstButton.block : false}
        >
          {props.firstButton.label}
        </Button>
      </div>
    );
  };

  const thirdsBlock = () => {
    return (
      <div className={classNameThirdsBlock}>
        <Button
          className="thirdsButton"
          onClick={() => {
            props.thirdsButton.onClick();
          }}
          disabled={props.thirdsButton?.block ? props.thirdsButton.block : false}
        >
          {props.thirdsButton.label}
        </Button>
      </div>
    );
  };

  const secondBlock = () => {
    return (
      <div className={classNameSecondBlock}>
        <Button
          className="secondButton"
          onClick={() => {
            props.secondButton.onClick();
          }}
          disabled={props.secondButton?.block ? props.secondButton.block : false}
        >
          {props.secondButton.label}
        </Button>
      </div>
    );
  };

  return (
    <div className="footerButton">
      {props.firstButton ? firstBlock() : null}
      {props.secondButton ? secondBlock() : null}
      {props.thirdsButton ? thirdsBlock() : null}
    </div>
  );
};
