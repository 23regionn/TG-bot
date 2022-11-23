import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import OfferFromCostumers from './offer-from-costumers';
import OfferFromCostumersDetail from './offer-from-costumers-detail';
import OfferFromCostumersUpdate from './offer-from-costumers-update';
import OfferFromCostumersDeleteDialog from './offer-from-costumers-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={OfferFromCostumersUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={OfferFromCostumersUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={OfferFromCostumersDetail} />
      <ErrorBoundaryRoute path={match.url} component={OfferFromCostumers} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={OfferFromCostumersDeleteDialog} />
  </>
);

export default Routes;
