import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ITradeShopLog, defaultValue } from 'app/shared/model/trade-shop-log.model';

export const ACTION_TYPES = {
  FETCH_TRADESHOPLOG_LIST: 'tradeShopLog/FETCH_TRADESHOPLOG_LIST',
  FETCH_TRADESHOPLOG: 'tradeShopLog/FETCH_TRADESHOPLOG',
  CREATE_TRADESHOPLOG: 'tradeShopLog/CREATE_TRADESHOPLOG',
  UPDATE_TRADESHOPLOG: 'tradeShopLog/UPDATE_TRADESHOPLOG',
  PARTIAL_UPDATE_TRADESHOPLOG: 'tradeShopLog/PARTIAL_UPDATE_TRADESHOPLOG',
  DELETE_TRADESHOPLOG: 'tradeShopLog/DELETE_TRADESHOPLOG',
  RESET: 'tradeShopLog/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ITradeShopLog>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type TradeShopLogState = Readonly<typeof initialState>;

// Reducer

export default (state: TradeShopLogState = initialState, action): TradeShopLogState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_TRADESHOPLOG_LIST):
    case REQUEST(ACTION_TYPES.FETCH_TRADESHOPLOG):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_TRADESHOPLOG):
    case REQUEST(ACTION_TYPES.UPDATE_TRADESHOPLOG):
    case REQUEST(ACTION_TYPES.DELETE_TRADESHOPLOG):
    case REQUEST(ACTION_TYPES.PARTIAL_UPDATE_TRADESHOPLOG):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_TRADESHOPLOG_LIST):
    case FAILURE(ACTION_TYPES.FETCH_TRADESHOPLOG):
    case FAILURE(ACTION_TYPES.CREATE_TRADESHOPLOG):
    case FAILURE(ACTION_TYPES.UPDATE_TRADESHOPLOG):
    case FAILURE(ACTION_TYPES.PARTIAL_UPDATE_TRADESHOPLOG):
    case FAILURE(ACTION_TYPES.DELETE_TRADESHOPLOG):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_TRADESHOPLOG_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_TRADESHOPLOG):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_TRADESHOPLOG):
    case SUCCESS(ACTION_TYPES.UPDATE_TRADESHOPLOG):
    case SUCCESS(ACTION_TYPES.PARTIAL_UPDATE_TRADESHOPLOG):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_TRADESHOPLOG):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {},
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState,
      };
    default:
      return state;
  }
};

const apiUrl = 'api/trade-shop-logs';

// Actions

export const getEntities: ICrudGetAllAction<ITradeShopLog> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_TRADESHOPLOG_LIST,
  payload: axios.get<ITradeShopLog>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<ITradeShopLog> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_TRADESHOPLOG,
    payload: axios.get<ITradeShopLog>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<ITradeShopLog> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_TRADESHOPLOG,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ITradeShopLog> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_TRADESHOPLOG,
    payload: axios.put(`${apiUrl}/${entity.id}`, cleanEntity(entity)),
  });
  return result;
};

export const partialUpdate: ICrudPutAction<ITradeShopLog> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.PARTIAL_UPDATE_TRADESHOPLOG,
    payload: axios.patch(`${apiUrl}/${entity.id}`, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<ITradeShopLog> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_TRADESHOPLOG,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
