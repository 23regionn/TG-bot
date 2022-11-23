import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ChanellLog from './chanell-log';
import ChanellLogDetail from './chanell-log-detail';
import ChanellLogUpdate from './chanell-log-update';
import ChanellLogDeleteDialog from './chanell-log-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ChanellLogUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ChanellLogUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ChanellLogDetail} />
      <ErrorBoundaryRoute path={match.url} component={ChanellLog} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={ChanellLogDeleteDialog} />
  </>
);

export default Routes;
