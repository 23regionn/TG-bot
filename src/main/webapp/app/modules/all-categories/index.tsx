import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import AllCategoriesTest from './all-categories-test';
import AllCategories from 'app/modules/all-categories/all-categories';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute path={match.url} component={AllCategories} />
      <ErrorBoundaryRoute exact path={`${match.url}/test`} component={AllCategoriesTest} />
    </Switch>
  </>
);

export default Routes;
