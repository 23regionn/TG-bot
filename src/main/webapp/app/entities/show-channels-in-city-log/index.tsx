import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ShowChannelsInCityLog from './show-channels-in-city-log';
import ShowChannelsInCityLogDetail from './show-channels-in-city-log-detail';
import ShowChannelsInCityLogUpdate from './show-channels-in-city-log-update';
import ShowChannelsInCityLogDeleteDialog from './show-channels-in-city-log-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ShowChannelsInCityLogUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ShowChannelsInCityLogUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ShowChannelsInCityLogDetail} />
      <ErrorBoundaryRoute path={match.url} component={ShowChannelsInCityLog} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={ShowChannelsInCityLogDeleteDialog} />
  </>
);

export default Routes;
