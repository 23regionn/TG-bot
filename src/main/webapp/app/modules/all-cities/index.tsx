import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import AllCities from 'app/modules/all-cities/all-cities';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute path={`${match.url}`} component={AllCities} />
    </Switch>
  </>
);

export default Routes;
