import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import TGUserLog from './tg-user-log';
import TGUserLogDetail from './tg-user-log-detail';
import TGUserLogUpdate from './tg-user-log-update';
import TGUserLogDeleteDialog from './tg-user-log-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={TGUserLogUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={TGUserLogUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={TGUserLogDetail} />
      <ErrorBoundaryRoute path={match.url} component={TGUserLog} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={TGUserLogDeleteDialog} />
  </>
);

export default Routes;
