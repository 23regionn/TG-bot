import './sidebar-component.scss';
import React, { Component, useEffect, useState } from 'react';
import { Flex_CSS, Height_CSS } from 'app/shared/component/LofTA-component/sidebar-component/enumParam';

interface Params {
  header?: any;
  body?: any;
  filter?: boolean | false;
  statusSidebar: boolean | false;
}

export const SidebarComponent = (props: Params) => {
  class StateSidebarFunction {
    static closeSidebar = () => {
      setSidebarStatus('sidebar-main close');
      setBackgroundStatus('sidebar close-background');
    };

    static openSidebar = () => {
      setSidebarStatus('sidebar-main');
      setBackgroundStatus('sidebar');
    };
  }

  const [sidebarStatus, setSidebarStatus] = useState('sidebar-main close');
  const [backgroundStatus, setBackgroundStatus] = useState('sidebar close-background');
  const header = () => {
    let flag = true;
    let resultClass = 'sidebar-header';
    props.header ? (resultClass = resultHeaderClass(resultClass)) : (flag = false);

    return flag ? (
      <div className={resultClass}>{props.header.content ? props.header.content : null}</div>
    ) : (
      <div className={resultClass}></div>
    );
  };
  const body = () => {
    let flag;
    const resultClass = 'sidebar-content';
    props.body ? (flag = true) : (flag = false);

    return <div className={resultClass}>{flag ? props.body.content : null}</div>;
  };
  const resultHeaderClass = value => {
    switch (props.header.height ? props.header.height : Height_CSS.HEIGHT_NORMAL) {
      case Height_CSS.HEIGHT_NORMAL: {
        break;
      }
      case Height_CSS.HEIGHT_LOW: {
        value += ' header-height-low';
        break;
      }
      case Height_CSS.HEIGHT_BIG: {
        value += ' header-height-big';
        break;
      }
      default: {
        break;
      }
    }
    switch (props.header.flex ? props.header.flex : Flex_CSS.FLEX_ROW) {
      case Flex_CSS.FLEX_ROW: {
        break;
      }
      case Flex_CSS.FLEX_COLUMN: {
        value += ' header-flex-column';
        break;
      }
      default: {
        break;
      }
    }

    return value;
  };

  useEffect(() => {
    props.statusSidebar ? StateSidebarFunction.openSidebar() : StateSidebarFunction.closeSidebar();
  }, [props.statusSidebar]);

  return (
    <div className={backgroundStatus}>
      <div className={sidebarStatus}>
        {header()}
        {body()}
        <div className="sidebar-footer"></div>
      </div>
    </div>
  );
};
