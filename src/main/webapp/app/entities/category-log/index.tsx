import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import CategoryLog from './category-log';
import CategoryLogDetail from './category-log-detail';
import CategoryLogUpdate from './category-log-update';
import CategoryLogDeleteDialog from './category-log-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={CategoryLogUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={CategoryLogUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={CategoryLogDetail} />
      <ErrorBoundaryRoute path={match.url} component={CategoryLog} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={CategoryLogDeleteDialog} />
  </>
);

export default Routes;
