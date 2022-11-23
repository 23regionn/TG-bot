import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IBalance, defaultValue } from 'app/shared/model/balance.model';

export const ACTION_TYPES = {
  FETCH_BALANCE_LIST: 'balance/FETCH_BALANCE_LIST',
  FETCH_BALANCE: 'balance/FETCH_BALANCE',
  CREATE_BALANCE: 'balance/CREATE_BALANCE',
  UPDATE_BALANCE: 'balance/UPDATE_BALANCE',
  PARTIAL_UPDATE_BALANCE: 'balance/PARTIAL_UPDATE_BALANCE',
  DELETE_BALANCE: 'balance/DELETE_BALANCE',
  RESET: 'balance/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IBalance>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type BalanceState = Readonly<typeof initialState>;

// Reducer

export default (state: BalanceState = initialState, action): BalanceState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_BALANCE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_BALANCE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_BALANCE):
    case REQUEST(ACTION_TYPES.UPDATE_BALANCE):
    case REQUEST(ACTION_TYPES.DELETE_BALANCE):
    case REQUEST(ACTION_TYPES.PARTIAL_UPDATE_BALANCE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_BALANCE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_BALANCE):
    case FAILURE(ACTION_TYPES.CREATE_BALANCE):
    case FAILURE(ACTION_TYPES.UPDATE_BALANCE):
    case FAILURE(ACTION_TYPES.PARTIAL_UPDATE_BALANCE):
    case FAILURE(ACTION_TYPES.DELETE_BALANCE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_BALANCE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_BALANCE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_BALANCE):
    case SUCCESS(ACTION_TYPES.UPDATE_BALANCE):
    case SUCCESS(ACTION_TYPES.PARTIAL_UPDATE_BALANCE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_BALANCE):
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

const apiUrl = 'api/balances';

// Actions

export const getEntities: ICrudGetAllAction<IBalance> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_BALANCE_LIST,
  payload: axios.get<IBalance>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<IBalance> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_BALANCE,
    payload: axios.get<IBalance>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IBalance> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_BALANCE,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IBalance> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_BALANCE,
    payload: axios.put(`${apiUrl}/${entity.id}`, cleanEntity(entity)),
  });
  return result;
};

export const partialUpdate: ICrudPutAction<IBalance> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.PARTIAL_UPDATE_BALANCE,
    payload: axios.patch(`${apiUrl}/${entity.id}`, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IBalance> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_BALANCE,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
