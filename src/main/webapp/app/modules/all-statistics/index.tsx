import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import AllStatistics from 'app/modules/all-statistics/all-statistics';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute path={`${match.url}`} component={AllStatistics} />
    </Switch>
  </>
);

export default Routes;
