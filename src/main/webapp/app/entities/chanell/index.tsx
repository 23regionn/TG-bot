import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Chanell from './chanell';
import ChanellDetail from './chanell-detail';
import ChanellUpdate from './chanell-update';
import ChanellDeleteDialog from './chanell-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ChanellUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ChanellUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ChanellDetail} />
      <ErrorBoundaryRoute path={match.url} component={Chanell} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={ChanellDeleteDialog} />
  </>
);

export default Routes;
