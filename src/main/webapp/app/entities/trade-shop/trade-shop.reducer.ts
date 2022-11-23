import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ITradeShop, defaultValue } from 'app/shared/model/trade-shop.model';

export const ACTION_TYPES = {
  FETCH_TRADESHOP_LIST: 'tradeShop/FETCH_TRADESHOP_LIST',
  FETCH_TRADESHOP: 'tradeShop/FETCH_TRADESHOP',
  CREATE_TRADESHOP: 'tradeShop/CREATE_TRADESHOP',
  UPDATE_TRADESHOP: 'tradeShop/UPDATE_TRADESHOP',
  PARTIAL_UPDATE_TRADESHOP: 'tradeShop/PARTIAL_UPDATE_TRADESHOP',
  DELETE_TRADESHOP: 'tradeShop/DELETE_TRADESHOP',
  RESET: 'tradeShop/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ITradeShop>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type TradeShopState = Readonly<typeof initialState>;

// Reducer

export default (state: TradeShopState = initialState, action): TradeShopState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_TRADESHOP_LIST):
    case REQUEST(ACTION_TYPES.FETCH_TRADESHOP):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_TRADESHOP):
    case REQUEST(ACTION_TYPES.UPDATE_TRADESHOP):
    case REQUEST(ACTION_TYPES.DELETE_TRADESHOP):
    case REQUEST(ACTION_TYPES.PARTIAL_UPDATE_TRADESHOP):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_TRADESHOP_LIST):
    case FAILURE(ACTION_TYPES.FETCH_TRADESHOP):
    case FAILURE(ACTION_TYPES.CREATE_TRADESHOP):
    case FAILURE(ACTION_TYPES.UPDATE_TRADESHOP):
    case FAILURE(ACTION_TYPES.PARTIAL_UPDATE_TRADESHOP):
    case FAILURE(ACTION_TYPES.DELETE_TRADESHOP):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_TRADESHOP_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_TRADESHOP):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_TRADESHOP):
    case SUCCESS(ACTION_TYPES.UPDATE_TRADESHOP):
    case SUCCESS(ACTION_TYPES.PARTIAL_UPDATE_TRADESHOP):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_TRADESHOP):
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

const apiUrl = 'api/trade-shops';

// Actions

export const getEntities: ICrudGetAllAction<ITradeShop> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_TRADESHOP_LIST,
  payload: axios.get<ITradeShop>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<ITradeShop> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_TRADESHOP,
    payload: axios.get<ITradeShop>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<ITradeShop> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_TRADESHOP,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ITradeShop> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_TRADESHOP,
    payload: axios.put(`${apiUrl}/${entity.id}`, cleanEntity(entity)),
  });
  return result;
};

export const partialUpdate: ICrudPutAction<ITradeShop> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.PARTIAL_UPDATE_TRADESHOP,
    payload: axios.patch(`${apiUrl}/${entity.id}`, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<ITradeShop> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_TRADESHOP,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
