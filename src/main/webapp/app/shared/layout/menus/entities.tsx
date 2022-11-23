import React from 'react';
import MenuItem from 'app/shared/layout/menus/menu-item';

import { NavDropdown } from './menu-components';

export const EntitiesMenu = props => (
  <NavDropdown icon="th-list" name="Entities" id="entity-menu" data-cy="entity" style={{ maxHeight: '80vh', overflow: 'auto' }}>
    <MenuItem icon="asterisk" to="/tg-user">
      TG User
    </MenuItem>
    <MenuItem icon="asterisk" to="/tg-user-log">
      TG User Log
    </MenuItem>
    <MenuItem icon="asterisk" to="/category">
      Category
    </MenuItem>
    <MenuItem icon="asterisk" to="/category-log">
      Category Log
    </MenuItem>
    <MenuItem icon="asterisk" to="/chanell">
      Chanell
    </MenuItem>
    <MenuItem icon="asterisk" to="/chanell-log">
      Chanell Log
    </MenuItem>
    <MenuItem icon="asterisk" to="/review">
      Review
    </MenuItem>
    <MenuItem icon="asterisk" to="/offer-from-costumers">
      Offer From Costumers
    </MenuItem>
    <MenuItem icon="asterisk" to="/offer-from-costumers-log">
      Offer From Costumers Log
    </MenuItem>
    <MenuItem icon="asterisk" to="/pays">
      Pays
    </MenuItem>
    <MenuItem icon="asterisk" to="/links-by-category-in-top">
      Links By Category In Top
    </MenuItem>
    <MenuItem icon="asterisk" to="/links-by-category-in-top-log">
      Links By Category In Top Log
    </MenuItem>
    <MenuItem icon="asterisk" to="/trade-shop">
      Trade Shop
    </MenuItem>
    <MenuItem icon="asterisk" to="/trade-shop-log">
      Trade Shop Log
    </MenuItem>
    <MenuItem icon="asterisk" to="/members-trade-deal">
      Members Trade Deal
    </MenuItem>
    <MenuItem icon="asterisk" to="/members-trade-deal-log">
      Members Trade Deal Log
    </MenuItem>
    <MenuItem icon="asterisk" to="/balance">
      Balance
    </MenuItem>
    <MenuItem icon="asterisk" to="/balance-log">
      Balance Log
    </MenuItem>
    <MenuItem icon="asterisk" to="/config-table">
      Config Table
    </MenuItem>
    {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
  </NavDropdown>
);
