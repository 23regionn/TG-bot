import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import AllChannelsByCategory from 'app/modules/all-channels/all-channels-by-category';
import AllChannelsByCityAndCategory from 'app/modules/all-channels/all-channels-by-city-and-category';
import AllRelChannelsByCategory from 'app/modules/all_rel_categories_channels/all-rel-channels-by-category';
import AllChannels from 'app/modules/all-channels/all-channels';
import AllRelChannelsCityCategory from 'app/modules/all-channels/all-rel-channels-city-category';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute path={`${match.url}/by-category-id/:id`} component={AllChannelsByCategory} />
      <ErrorBoundaryRoute path={`${match.url}/rel/by-category-id/:id`} component={AllRelChannelsByCategory} />
      <ErrorBoundaryRoute path={`${match.url}/rel/channels-city-category/:relCategoryCityId`} component={AllRelChannelsCityCategory} />
      <ErrorBoundaryRoute path={`${match.url}/by-city-and-category/:idCity/:idCat`} component={AllChannelsByCityAndCategory} />
      <ErrorBoundaryRoute path={match.url} component={AllChannels} />
    </Switch>
  </>
);

export default Routes;
