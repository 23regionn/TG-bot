import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ConfigTable from './config-table';
import ConfigTableDetail from './config-table-detail';
import ConfigTableUpdate from './config-table-update';
import ConfigTableDeleteDialog from './config-table-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ConfigTableUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ConfigTableUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ConfigTableDetail} />
      <ErrorBoundaryRoute path={match.url} component={ConfigTable} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={ConfigTableDeleteDialog} />
  </>
);

export default Routes;
