import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import RelCategoryCity from './rel-category-city';
import RelCategoryCityDetail from './rel-category-city-detail';
import RelCategoryCityUpdate from './rel-category-city-update';
import RelCategoryCityDeleteDialog from './rel-category-city-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={RelCategoryCityUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={RelCategoryCityUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={RelCategoryCityDetail} />
      <ErrorBoundaryRoute path={match.url} component={RelCategoryCity} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={RelCategoryCityDeleteDialog} />
  </>
);

export default Routes;
