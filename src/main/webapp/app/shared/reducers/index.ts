import { combineReducers } from 'redux';
import { loadingBarReducer as loadingBar } from 'react-redux-loading-bar';

import authentication, { AuthenticationState } from './authentication';
import applicationProfile, { ApplicationProfileState } from './application-profile';

import administration, { AdministrationState } from 'app/modules/administration/administration.reducer';
import userManagement, { UserManagementState } from 'app/modules/administration/user-management/user-management.reducer';
import register, { RegisterState } from 'app/modules/account/register/register.reducer';
import activate, { ActivateState } from 'app/modules/account/activate/activate.reducer';
import password, { PasswordState } from 'app/modules/account/password/password.reducer';
import settings, { SettingsState } from 'app/modules/account/settings/settings.reducer';
import passwordReset, { PasswordResetState } from 'app/modules/account/password-reset/password-reset.reducer';
// prettier-ignore
import tGUser, {
  TGUserState
} from 'app/entities/tg-user/tg-user.reducer';
// prettier-ignore
import tGUserLog, {
  TGUserLogState
} from 'app/entities/tg-user-log/tg-user-log.reducer';
// prettier-ignore
import category, {
  CategoryState
} from 'app/entities/category/category.reducer';
import categoryList, { AllCategoriesState } from 'app/modules/all-categories/all-categories.reducer';
// prettier-ignore
import categoryLog, {
  CategoryLogState
} from 'app/entities/category-log/category-log.reducer';
// prettier-ignore
import chanell, {
  ChanellState
} from 'app/entities/chanell/chanell.reducer';
// prettier-ignore
import chanellLog, {
  ChanellLogState
} from 'app/entities/chanell-log/chanell-log.reducer';
// prettier-ignore
import review, {
  ReviewState
} from 'app/entities/review/review.reducer';
// prettier-ignore
import offerFromCostumers, {
  OfferFromCostumersState
} from 'app/entities/offer-from-costumers/offer-from-costumers.reducer';
// prettier-ignore
import offerFromCostumersLog, {
  OfferFromCostumersLogState
} from 'app/entities/offer-from-costumers-log/offer-from-costumers-log.reducer';
// prettier-ignore
import pays, {
  PaysState
} from 'app/entities/pays/pays.reducer';
// prettier-ignore
import linksByCategoryInTop, {
  LinksByCategoryInTopState
} from 'app/entities/links-by-category-in-top/links-by-category-in-top.reducer';
// prettier-ignore
import linksByCategoryInTopLog, {
  LinksByCategoryInTopLogState
} from 'app/entities/links-by-category-in-top-log/links-by-category-in-top-log.reducer';
// prettier-ignore
import tradeShop, {
  TradeShopState
} from 'app/entities/trade-shop/trade-shop.reducer';
// prettier-ignore
import tradeShopLog, {
  TradeShopLogState
} from 'app/entities/trade-shop-log/trade-shop-log.reducer';
// prettier-ignore
import membersTradeDeal, {
  MembersTradeDealState
} from 'app/entities/members-trade-deal/members-trade-deal.reducer';
// prettier-ignore
import membersTradeDealLog, {
  MembersTradeDealLogState
} from 'app/entities/members-trade-deal-log/members-trade-deal-log.reducer';
// prettier-ignore
import balance, {
  BalanceState
} from 'app/entities/balance/balance.reducer';
// prettier-ignore
import balanceLog, {
  BalanceLogState
} from 'app/entities/balance-log/balance-log.reducer';
// prettier-ignore
import configTable, {
  ConfigTableState
} from 'app/entities/config-table/config-table.reducer';
// prettier-ignore
import messegePannel, {
  MessegePannelState
} from 'app/entities/messege-pannel/messege-pannel.reducer';
// prettier-ignore
import editChannels, {
  EditChannelsState
} from 'app/entities/edit-channels/edit-channels.reducer';
// prettier-ignore
import city, {
  CityState
} from 'app/entities/city/city.reducer';
// prettier-ignore
import manager, {
  ManagerState
} from 'app/entities/manager/manager.reducer';
// prettier-ignore
import admin, {
  AdminState
} from 'app/entities/admin/admin.reducer';

import relCategoryChannels, { RelCategoryChannelsState } from 'app/entities/rel-category-channels/rel-category-channels.reducer';

import relCategoryCity, { RelCategoryCityState } from 'app/entities/rel-category-city/rel-category-city.reducer';

import relCategoryCityChannels, {
  RelCategoryCityChannelsState,
} from 'app/entities/rel-category-city-channels/rel-category-city-channels.reducer';
// prettier-ignore
import searchTypeLog, {
  SearchTypeLogState
} from 'app/entities/search-type-log/search-type-log.reducer';
// prettier-ignore
import countChannelClickPageLog, {
  CountChannelClickPageLogState
} from 'app/entities/count-channel-click-page-log/count-channel-click-page-log.reducer';

// prettier-ignore
import showChannelsInCategoryLog, {
  ShowChannelsInCategoryLogState
} from 'app/entities/show-channels-in-category-log/show-channels-in-category-log.reducer';
// prettier-ignore
import showChannelsInCityLog, {
  ShowChannelsInCityLogState
} from 'app/entities/show-channels-in-city-log/show-channels-in-city-log.reducer';
// prettier-ignore
import auditChannelsLog, {
  AuditChannelsLogState
} from 'app/entities/audit-channels-log/audit-channels-log.reducer';

// prettier-ignore
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

export interface IRootState {
  readonly authentication: AuthenticationState;
  readonly applicationProfile: ApplicationProfileState;
  readonly administration: AdministrationState;
  readonly userManagement: UserManagementState;
  readonly register: RegisterState;
  readonly activate: ActivateState;
  readonly passwordReset: PasswordResetState;
  readonly password: PasswordState;
  readonly settings: SettingsState;
  readonly tGUser: TGUserState;
  readonly tGUserLog: TGUserLogState;
  readonly category: CategoryState;
  readonly categoryList: AllCategoriesState;
  readonly categoryLog: CategoryLogState;
  readonly chanell: ChanellState;
  readonly chanellLog: ChanellLogState;
  readonly review: ReviewState;
  readonly offerFromCostumers: OfferFromCostumersState;
  readonly offerFromCostumersLog: OfferFromCostumersLogState;
  readonly pays: PaysState;
  readonly linksByCategoryInTop: LinksByCategoryInTopState;
  readonly linksByCategoryInTopLog: LinksByCategoryInTopLogState;
  readonly tradeShop: TradeShopState;
  readonly tradeShopLog: TradeShopLogState;
  readonly membersTradeDeal: MembersTradeDealState;
  readonly membersTradeDealLog: MembersTradeDealLogState;
  readonly balance: BalanceState;
  readonly balanceLog: BalanceLogState;
  readonly configTable: ConfigTableState;
  readonly messegePannel: MessegePannelState;
  readonly editChannels: EditChannelsState;
  readonly city: CityState;
  readonly manager: ManagerState;
  readonly admin: AdminState;
  readonly relCategoryChannels: RelCategoryChannelsState;
  readonly relCategoryCity: RelCategoryCityState;
  readonly relCategoryCityChannels: RelCategoryCityChannelsState;
  readonly searchTypeLog: SearchTypeLogState;
  readonly countChannelClickPageLog: CountChannelClickPageLogState;
  readonly showChannelsInCategoryLog: ShowChannelsInCategoryLogState;
  readonly showChannelsInCityLog: ShowChannelsInCityLogState;
  readonly auditChannelsLog: AuditChannelsLogState;
  /* jhipster-needle-add-reducer-type - JHipster will add reducer type here */
  readonly loadingBar: any;
}

const rootReducer = combineReducers<IRootState>({
  authentication,
  applicationProfile,
  administration,
  userManagement,
  register,
  activate,
  passwordReset,
  password,
  settings,
  tGUser,
  tGUserLog,
  category,
  categoryList,
  categoryLog,
  chanell,
  chanellLog,
  review,
  offerFromCostumers,
  offerFromCostumersLog,
  pays,
  linksByCategoryInTop,
  linksByCategoryInTopLog,
  tradeShop,
  tradeShopLog,
  membersTradeDeal,
  membersTradeDealLog,
  balance,
  balanceLog,
  configTable,
  messegePannel,
  editChannels,
  city,
  manager,
  admin,
  relCategoryChannels,
  relCategoryCity,
  relCategoryCityChannels,
  searchTypeLog,
  countChannelClickPageLog,
  showChannelsInCategoryLog,
  showChannelsInCityLog,
  auditChannelsLog,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
  loadingBar,
});

export default rootReducer;
