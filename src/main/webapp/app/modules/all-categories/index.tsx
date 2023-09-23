import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import AllCategoriesTest from './all-categories-test';
import AllCategories from 'app/modules/all-categories/all-categories';
import AllCategoriesByCityId from 'app/modules/all-categories/all-categories-by-city-id';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/test`} component={AllCategoriesTest} />
      <ErrorBoundaryRoute exact path={`${match.url}/by-city-id/:id`} component={AllCategoriesByCityId} />
      <ErrorBoundaryRoute path={match.url} component={AllCategories} />
    </Switch>
  </>
);

export default Routes;
