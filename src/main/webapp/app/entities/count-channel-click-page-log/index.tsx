import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import CountChannelClickPageLog from './count-channel-click-page-log';
import CountChannelClickPageLogDetail from './count-channel-click-page-log-detail';
import CountChannelClickPageLogUpdate from './count-channel-click-page-log-update';
import CountChannelClickPageLogDeleteDialog from './count-channel-click-page-log-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={CountChannelClickPageLogUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={CountChannelClickPageLogUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={CountChannelClickPageLogDetail} />
      <ErrorBoundaryRoute path={match.url} component={CountChannelClickPageLog} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={CountChannelClickPageLogDeleteDialog} />
  </>
);

export default Routes;
