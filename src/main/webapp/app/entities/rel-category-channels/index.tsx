import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import RelCategoryChannels from './rel-category-channels';
import RelCategoryChannelsDetail from './rel-category-channels-detail';
import RelCategoryChannelsUpdate from './rel-category-channels-update';
import RelCategoryChannelsDeleteDialog from './rel-category-channels-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={RelCategoryChannelsUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={RelCategoryChannelsUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={RelCategoryChannelsDetail} />
      <ErrorBoundaryRoute path={match.url} component={RelCategoryChannels} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={RelCategoryChannelsDeleteDialog} />
  </>
);

export default Routes;
