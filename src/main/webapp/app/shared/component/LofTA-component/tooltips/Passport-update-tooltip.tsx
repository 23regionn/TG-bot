import { Tooltip } from 'primereact/tooltip';
import React from 'react';
import { translate } from 'react-jhipster';
import './passport-update-tooltip.scss';

const PassportUpdateTooltip = () => {
  return (
    <>
      <Tooltip target=".pi-info-circle" />
      <i className="pi pi-info-circle" data-pr-tooltip={translate('eaistkApp.passportIns.tooltips.passportUpdate')}></i>
    </>
  );
};

export default PassportUpdateTooltip;
