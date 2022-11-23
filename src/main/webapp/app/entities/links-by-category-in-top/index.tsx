import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import LinksByCategoryInTop from './links-by-category-in-top';
import LinksByCategoryInTopDetail from './links-by-category-in-top-detail';
import LinksByCategoryInTopUpdate from './links-by-category-in-top-update';
import LinksByCategoryInTopDeleteDialog from './links-by-category-in-top-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={LinksByCategoryInTopUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={LinksByCategoryInTopUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={LinksByCategoryInTopDetail} />
      <ErrorBoundaryRoute path={match.url} component={LinksByCategoryInTop} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={LinksByCategoryInTopDeleteDialog} />
  </>
);

export default Routes;
