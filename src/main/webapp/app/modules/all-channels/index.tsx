import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import AllChannelsByCategory from 'app/modules/all-channels/all-channels-by-category';
import AllChannelsByCiyAndCategory from 'app/modules/all-channels/all-channels-by-city-and-category';

const Routes = ({ match }) => (
  <>
    <Switch>
      {/*<ErrorBoundaryRoute path={match.url} component={AllCategories} />  тут будут все каналы*/}
      <ErrorBoundaryRoute path={`${match.url}/by-category-id/:id`} component={AllChannelsByCategory} />
      <ErrorBoundaryRoute path={`${match.url}/by-city-and-category/:idCity/:idCat`} component={AllChannelsByCiyAndCategory} />
    </Switch>
  </>
);

export default Routes;
