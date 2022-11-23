import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import OfferFromCostumersLog from './offer-from-costumers-log';
import OfferFromCostumersLogDetail from './offer-from-costumers-log-detail';
import OfferFromCostumersLogUpdate from './offer-from-costumers-log-update';
import OfferFromCostumersLogDeleteDialog from './offer-from-costumers-log-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={OfferFromCostumersLogUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={OfferFromCostumersLogUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={OfferFromCostumersLogDetail} />
      <ErrorBoundaryRoute path={match.url} component={OfferFromCostumersLog} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={OfferFromCostumersLogDeleteDialog} />
  </>
);

export default Routes;
