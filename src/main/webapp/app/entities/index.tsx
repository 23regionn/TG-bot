import React from 'react';
import { Switch } from 'react-router-dom';

// eslint-disable-next-line @typescript-eslint/no-unused-vars
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import TGUser from './tg-user';
import TGUserLog from './tg-user-log';
import Category from './category';
import CategoryLog from './category-log';
import Chanell from './chanell';
import ChanellLog from './chanell-log';
import Review from './review';
import OfferFromCostumers from './offer-from-costumers';
import OfferFromCostumersLog from './offer-from-costumers-log';
import Pays from './pays';
import LinksByCategoryInTop from './links-by-category-in-top';
import LinksByCategoryInTopLog from './links-by-category-in-top-log';
import TradeShop from './trade-shop';
import TradeShopLog from './trade-shop-log';
import MembersTradeDeal from './members-trade-deal';
import MembersTradeDealLog from './members-trade-deal-log';
import Balance from './balance';
import BalanceLog from './balance-log';
import ConfigTable from './config-table';
import MessegePannel from './messege-pannel';
import EditChannels from './edit-channels';
import City from './city';
import Manager from './manager';
import Admin from './admin';
import AllCategories from '../modules/all-categories/index';
import AllChannels from '../modules/all-channels/index';
import AllCities from '../modules/all-cities/index';
import RelCategoryChannels from './rel-category-channels';
import RelCategoryCity from './rel-category-city';
import RelCategoryCityChannels from './rel-category-city-channels';
import SearchTypeLog from './search-type-log';
import CountChannelClickPageLog from './count-channel-click-page-log';
import AllStatistics from '../modules/all-statistics/index';
import ShowChannelsInCategoryLog from './show-channels-in-category-log';
import ShowChannelsInCityLog from './show-channels-in-city-log';
import AuditChannelsLog from './audit-channels-log';

/* jhipster-needle-add-route-import - JHipster will add routes here */

const Routes = ({ match }) => (
  <div>
    <Switch>
      {/* prettier-ignore */}
      <ErrorBoundaryRoute path={`${match.url}tg-user`} component={TGUser} />
      <ErrorBoundaryRoute path={`${match.url}tg-user-log`} component={TGUserLog} />
      <ErrorBoundaryRoute path={`${match.url}category`} component={Category} />
      <ErrorBoundaryRoute path={`${match.url}category-log`} component={CategoryLog} />
      <ErrorBoundaryRoute path={`${match.url}chanell`} component={Chanell} />
      <ErrorBoundaryRoute path={`${match.url}chanell-log`} component={ChanellLog} />
      <ErrorBoundaryRoute path={`${match.url}review`} component={Review} />
      <ErrorBoundaryRoute path={`${match.url}offer-from-costumers`} component={OfferFromCostumers} />
      <ErrorBoundaryRoute path={`${match.url}offer-from-costumers-log`} component={OfferFromCostumersLog} />
      <ErrorBoundaryRoute path={`${match.url}pays`} component={Pays} />
      <ErrorBoundaryRoute path={`${match.url}links-by-category-in-top`} component={LinksByCategoryInTop} />
      <ErrorBoundaryRoute path={`${match.url}links-by-category-in-top-log`} component={LinksByCategoryInTopLog} />
      <ErrorBoundaryRoute path={`${match.url}trade-shop`} component={TradeShop} />
      <ErrorBoundaryRoute path={`${match.url}trade-shop-log`} component={TradeShopLog} />
      <ErrorBoundaryRoute path={`${match.url}members-trade-deal`} component={MembersTradeDeal} />
      <ErrorBoundaryRoute path={`${match.url}members-trade-deal-log`} component={MembersTradeDealLog} />
      <ErrorBoundaryRoute path={`${match.url}balance`} component={Balance} />
      <ErrorBoundaryRoute path={`${match.url}balance-log`} component={BalanceLog} />
      <ErrorBoundaryRoute path={`${match.url}config-table`} component={ConfigTable} />
      <ErrorBoundaryRoute path={`${match.url}messege-pannel`} component={MessegePannel} />
      <ErrorBoundaryRoute path={`${match.url}edit-channels`} component={EditChannels} />
      <ErrorBoundaryRoute path={`${match.url}city`} component={City} />
      <ErrorBoundaryRoute path={`${match.url}manager`} component={Manager} />
      <ErrorBoundaryRoute path={`${match.url}entity-admin`} component={Admin} />
      {/* новые*/}
      <ErrorBoundaryRoute path={`${match.url}all-categories`} component={AllCategories} />
      <ErrorBoundaryRoute path={`${match.url}all-channels`} component={AllChannels} />
      <ErrorBoundaryRoute path={`${match.url}all-cities`} component={AllCities} />
      <ErrorBoundaryRoute path={`${match.url}all-statistics`} component={AllStatistics} />

      <ErrorBoundaryRoute path={`${match.url}rel-category-channels`} component={RelCategoryChannels} />
      <ErrorBoundaryRoute path={`${match.url}rel-category-city`} component={RelCategoryCity} />
      <ErrorBoundaryRoute path={`${match.url}rel-category-city-channels`} component={RelCategoryCityChannels} />
      <ErrorBoundaryRoute path={`${match.url}search-type-log`} component={SearchTypeLog} />
      <ErrorBoundaryRoute path={`${match.url}count-channel-click-page-log`} component={CountChannelClickPageLog} />
      <ErrorBoundaryRoute path={`${match.url}show-channels-in-category-log`} component={ShowChannelsInCategoryLog} />
      <ErrorBoundaryRoute path={`${match.url}show-channels-in-city-log`} component={ShowChannelsInCityLog} />
      <ErrorBoundaryRoute path={`${match.url}audit-channels-log`} component={AuditChannelsLog} />
      {/* jhipster-needle-add-route-path - JHipster will add routes here */}
    </Switch>
  </div>
);

export default Routes;
