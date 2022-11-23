import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import TradeShopLog from './trade-shop-log';
import TradeShopLogDetail from './trade-shop-log-detail';
import TradeShopLogUpdate from './trade-shop-log-update';
import TradeShopLogDeleteDialog from './trade-shop-log-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={TradeShopLogUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={TradeShopLogUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={TradeShopLogDetail} />
      <ErrorBoundaryRoute path={match.url} component={TradeShopLog} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={TradeShopLogDeleteDialog} />
  </>
);

export default Routes;
