import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import LinksByCategoryInTopLog from './links-by-category-in-top-log';
import LinksByCategoryInTopLogDetail from './links-by-category-in-top-log-detail';
import LinksByCategoryInTopLogUpdate from './links-by-category-in-top-log-update';
import LinksByCategoryInTopLogDeleteDialog from './links-by-category-in-top-log-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={LinksByCategoryInTopLogUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={LinksByCategoryInTopLogUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={LinksByCategoryInTopLogDetail} />
      <ErrorBoundaryRoute path={match.url} component={LinksByCategoryInTopLog} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={LinksByCategoryInTopLogDeleteDialog} />
  </>
);

export default Routes;
