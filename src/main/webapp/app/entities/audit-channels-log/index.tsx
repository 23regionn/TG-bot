import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import AuditChannelsLog from './audit-channels-log';
import AuditChannelsLogDetail from './audit-channels-log-detail';
import AuditChannelsLogUpdate from './audit-channels-log-update';
import AuditChannelsLogDeleteDialog from './audit-channels-log-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={AuditChannelsLogUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={AuditChannelsLogUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={AuditChannelsLogDetail} />
      <ErrorBoundaryRoute path={match.url} component={AuditChannelsLog} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={AuditChannelsLogDeleteDialog} />
  </>
);

export default Routes;
