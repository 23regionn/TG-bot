import './left-menu-bar.scss';
import React, { useEffect, useState } from 'react';
import { Button } from 'primereact/button';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { ScrollPanel } from 'primereact/scrollpanel';
import { ContentMenu } from 'app/shared/component/LofTA-component/content/content-menu/content-menu';
import { NavLink } from 'react-router-dom';

interface Params {
  item: any;
  loadItemMenu: any;
  filter?: boolean | false;
  loading: any;
  switchRoute: any;
  nameValue: any;
  button?: any;
  loaderContent?: boolean | false;
  close?: boolean | false;
  statusOpen?: boolean | false;
}

export const LeftBarMenu = (props: Params) => {
  const [styleSide, setStyleSide] = useState('leftBar-sideMenu');

  const [iconButton, setIconButton] = useState('&#10096;');

  const [status, setStatus] = useState(false);

  function closeNav() {
    setStyleSide('leftBar-sideMenu margin-left');
  }
  function openNav() {
    setStyleSide('leftBar-sideMenu');
  }

  const NavUpdate = () => {
    setStatus(!status);
    if (!status) {
      closeNav();
    } else {
      openNav();
    }
    document.getElementById('burger').classList.toggle('active');
  };

  useEffect(() => {
    if (props.close === true) {
      if (props.statusOpen === false) {
        document.getElementById('burger').classList.toggle('active');
        NavUpdate();
      } else {
        document.getElementById('burger').classList.toggle('active');
      }
    }
  }, [props.statusOpen]);

  return (
    <div className="leftBar-container">
      <div className={styleSide}>
        <div className="leftBar-header m_header">
          <div className="mr-1 ml-1">
            <div className="leftBar-header-nav-back">
              <NavLink to="/list-of-technical-acceptances" className="leftBar-header-nav-link l_font-size">
                <FontAwesomeIcon icon="arrow-left" className="leftBar-header-link-icon" />
                {props.nameValue.nameLink}
              </NavLink>
            </div>
            <div className="leftBar-header-text b_style mt-2 ">
              <p className="leftBar-text-overflow">{props.nameValue.name}</p>
            </div>
            <div className="leftBar-header-last-element m_margin">
              <Button
                //  className="leftBar-header-button"
                className="button_change_custom"
                onClick={props.button.onClick}
              >
                <span>{props.button.nameButton}</span>
              </Button>
            </div>
            <div className="leftBar-sideMenu-closer-2">
              {props.close ? (
                <div id="burger" className="burger-arrow" onClick={NavUpdate}>
                  <span></span>
                  <span></span>
                  <span></span>
                </div>
              ) : (
                <></>
              )}
            </div>
          </div>
        </div>
        <div className="leftBar-menu m_height">
          <ScrollPanel className="leftBar-scroll-panel">
            <div className="d-flex flex-column mr-1 ml-1" id="left_bar_menu_content_menu">
              <ContentMenu value={props.item} onSelect={props.loadItemMenu} loading={props.loading} filter={props.filter} />
            </div>
          </ScrollPanel>
        </div>
        <div className="leftBar-footer"></div>
      </div>
      <ScrollPanel id="inspectionScrPanel">
        <div className="leftBar-content c_flex-direction">{props.switchRoute()}</div>
      </ScrollPanel>
      {/* <div className="leftBar-footer"></div> */}
    </div>
  );
};
