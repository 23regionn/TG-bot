import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ShowChannelsInCategoryLog from './show-channels-in-category-log';
import ShowChannelsInCategoryLogDetail from './show-channels-in-category-log-detail';
import ShowChannelsInCategoryLogUpdate from './show-channels-in-category-log-update';
import ShowChannelsInCategoryLogDeleteDialog from './show-channels-in-category-log-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ShowChannelsInCategoryLogUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ShowChannelsInCategoryLogUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ShowChannelsInCategoryLogDetail} />
      <ErrorBoundaryRoute path={match.url} component={ShowChannelsInCategoryLog} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={ShowChannelsInCategoryLogDeleteDialog} />
  </>
);

export default Routes;
