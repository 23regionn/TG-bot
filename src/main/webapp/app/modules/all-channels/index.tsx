import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import AllCategories from 'app/modules/all-channels/all-channels-by-category';

const Routes = ({ match }) => (
  <>
    <Switch>
      {/*<ErrorBoundaryRoute path={match.url} component={AllCategories} />  тут будут все каналы*/}
      <ErrorBoundaryRoute path={`${match.url}/by-category-id/:id`} component={AllCategories} />
    </Switch>
  </>
);

export default Routes;
