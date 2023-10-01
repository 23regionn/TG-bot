import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import RelCategoryCityChannels from './rel-category-city-channels';
import RelCategoryCityChannelsDetail from './rel-category-city-channels-detail';
import RelCategoryCityChannelsUpdate from './rel-category-city-channels-update';
import RelCategoryCityChannelsDeleteDialog from './rel-category-city-channels-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={RelCategoryCityChannelsUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={RelCategoryCityChannelsUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={RelCategoryCityChannelsDetail} />
      <ErrorBoundaryRoute path={match.url} component={RelCategoryCityChannels} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={RelCategoryCityChannelsDeleteDialog} />
  </>
);

export default Routes;
