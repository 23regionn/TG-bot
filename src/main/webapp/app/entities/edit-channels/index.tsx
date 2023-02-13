import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import EditChannels from './edit-channels';
import EditChannelsDetail from './edit-channels-detail';
import EditChannelsUpdate from './edit-channels-update';
import EditChannelsDeleteDialog from './edit-channels-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={EditChannelsUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={EditChannelsUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={EditChannelsDetail} />
      <ErrorBoundaryRoute path={match.url} component={EditChannels} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={EditChannelsDeleteDialog} />
  </>
);

export default Routes;
