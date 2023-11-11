import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import AllStatistics from 'app/modules/all-statistics/all-statistics';
import AllStatisticsClicks from 'app/modules/all-statistics/all-statistics-clicks';
import AllStatisticsCategory from 'app/modules/all-statistics/all-statistics-category';
import AllStatisticsCity from 'app/modules/all-statistics/all-statistics-city';
import AllStatisticsTgUsers from 'app/modules/all-statistics/all-statistics-tg-users';
import AllStatisticsCityCategory from 'app/modules/all-statistics/all-statistics-city-category';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute path={`${match.url}/clicks`} component={AllStatisticsClicks} />
      <ErrorBoundaryRoute path={`${match.url}/category`} component={AllStatisticsCategory} />
      <ErrorBoundaryRoute path={`${match.url}/city/category/:idCity`} component={AllStatisticsCityCategory} />
      <ErrorBoundaryRoute path={`${match.url}/city`} component={AllStatisticsCity} />
      <ErrorBoundaryRoute path={`${match.url}/tgUsers`} component={AllStatisticsTgUsers} />
      <ErrorBoundaryRoute path={`${match.url}`} component={AllStatistics} />
    </Switch>
  </>
);

export default Routes;
