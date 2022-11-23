import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import TradeShop from './trade-shop';
import TradeShopDetail from './trade-shop-detail';
import TradeShopUpdate from './trade-shop-update';
import TradeShopDeleteDialog from './trade-shop-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={TradeShopUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={TradeShopUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={TradeShopDetail} />
      <ErrorBoundaryRoute path={match.url} component={TradeShop} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={TradeShopDeleteDialog} />
  </>
);

export default Routes;
