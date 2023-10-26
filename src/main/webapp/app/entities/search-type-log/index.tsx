import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import SearchTypeLog from './search-type-log';
import SearchTypeLogDetail from './search-type-log-detail';
import SearchTypeLogUpdate from './search-type-log-update';
import SearchTypeLogDeleteDialog from './search-type-log-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={SearchTypeLogUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={SearchTypeLogUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={SearchTypeLogDetail} />
      <ErrorBoundaryRoute path={match.url} component={SearchTypeLog} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={SearchTypeLogDeleteDialog} />
  </>
);

export default Routes;
