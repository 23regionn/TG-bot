import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import MembersTradeDealLog from './members-trade-deal-log';
import MembersTradeDealLogDetail from './members-trade-deal-log-detail';
import MembersTradeDealLogUpdate from './members-trade-deal-log-update';
import MembersTradeDealLogDeleteDialog from './members-trade-deal-log-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={MembersTradeDealLogUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={MembersTradeDealLogUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={MembersTradeDealLogDetail} />
      <ErrorBoundaryRoute path={match.url} component={MembersTradeDealLog} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={MembersTradeDealLogDeleteDialog} />
  </>
);

export default Routes;
