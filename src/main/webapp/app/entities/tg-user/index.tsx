import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import TGUser from './tg-user';
import TGUserDetail from './tg-user-detail';
import TGUserUpdate from './tg-user-update';
import TGUserDeleteDialog from './tg-user-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={TGUserUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={TGUserUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={TGUserDetail} />
      <ErrorBoundaryRoute path={match.url} component={TGUser} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={TGUserDeleteDialog} />
  </>
);

export default Routes;
