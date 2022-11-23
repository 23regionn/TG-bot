import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import MembersTradeDeal from './members-trade-deal';
import MembersTradeDealDetail from './members-trade-deal-detail';
import MembersTradeDealUpdate from './members-trade-deal-update';
import MembersTradeDealDeleteDialog from './members-trade-deal-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={MembersTradeDealUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={MembersTradeDealUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={MembersTradeDealDetail} />
      <ErrorBoundaryRoute path={match.url} component={MembersTradeDeal} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={MembersTradeDealDeleteDialog} />
  </>
);

export default Routes;
