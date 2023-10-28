import React from 'react';

import { NavItem, NavLink, NavbarBrand } from 'reactstrap';
import { NavLink as Link } from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import appConfig from 'app/config/constants';

export const BrandIcon = props => (
  <div {...props} className="brand-icon">
    <img src="content/images/logo-jhipster.png" alt="Logo" />
  </div>
);

export const Brand = props => (
  <NavbarBrand tag={Link} to="/" className="brand-logo">
    <BrandIcon />
    <span className="brand-title">Gid</span>
    <span className="navbar-version">{appConfig.VERSION}</span>
  </NavbarBrand>
);

export const Home = props => (
  <NavItem>
    <NavLink tag={Link} to="/" className="d-flex align-items-center">
      <FontAwesomeIcon icon="home" />
      <span>Home</span>
    </NavLink>
  </NavItem>
);

export const AllCategoriesHeader = props => (
  <NavItem>
    <NavLink tag={Link} to="/all-categories" className="d-flex align-items-center">
      <FontAwesomeIcon icon="book" />
      <span>Все категории</span>
    </NavLink>
  </NavItem>
);

export const AllCitiesHeader = props => (
  <NavItem>
    <NavLink tag={Link} to="/all-cities" className="d-flex align-items-center">
      <FontAwesomeIcon icon="book" />
      <span>Все города</span>
    </NavLink>
  </NavItem>
);

export const AllChannelsHeader = props => (
  <NavItem>
    <NavLink tag={Link} to="/all-channels" className="d-flex align-items-center">
      <FontAwesomeIcon icon="book" />
      <span>Все каналы</span>
    </NavLink>
  </NavItem>
);

export const AllStatisticsHeader = props => (
  <NavItem>
    <NavLink tag={Link} to="/all-statistics" className="d-flex align-items-center">
      <FontAwesomeIcon icon="search" />
      <span>Статистика</span>
    </NavLink>
  </NavItem>
);
