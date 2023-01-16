import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import MessegePannel from './messege-pannel';
import MessegePannelDetail from './messege-pannel-detail';
import MessegePannelUpdate from './messege-pannel-update';
import MessegePannelDeleteDialog from './messege-pannel-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={MessegePannelUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={MessegePannelUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={MessegePannelDetail} />
      <ErrorBoundaryRoute path={match.url} component={MessegePannel} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={MessegePannelDeleteDialog} />
  </>
);

export default Routes;
