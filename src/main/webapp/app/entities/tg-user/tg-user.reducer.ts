import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ITGUser, defaultValue } from 'app/shared/model/tg-user.model';

export const ACTION_TYPES = {
  FETCH_TGUSER_LIST: 'tGUser/FETCH_TGUSER_LIST',
  FETCH_TGUSER_COUNT: 'tGUser/FETCH_TGUSER_COUNT',
  FETCH_TGUSER_BASE_STATISTICS: 'tGUser/FETCH_TGUSER_BASE_STATISTICS',
  FETCH_TGUSER_COUNT_BY_DATES: 'tGUser/FETCH_TGUSER_COUNT_BY_DATES',
  FETCH_TGUSER: 'tGUser/FETCH_TGUSER',
  CREATE_TGUSER: 'tGUser/CREATE_TGUSER',
  UPDATE_TGUSER: 'tGUser/UPDATE_TGUSER',
  PARTIAL_UPDATE_TGUSER: 'tGUser/PARTIAL_UPDATE_TGUSER',
  DELETE_TGUSER: 'tGUser/DELETE_TGUSER',
  RESET: 'tGUser/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ITGUser>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type TGUserState = Readonly<typeof initialState>;

// Reducer

export default (state: TGUserState = initialState, action): TGUserState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_TGUSER_LIST):
    case REQUEST(ACTION_TYPES.FETCH_TGUSER):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_TGUSER):
    case REQUEST(ACTION_TYPES.UPDATE_TGUSER):
    case REQUEST(ACTION_TYPES.DELETE_TGUSER):
    case REQUEST(ACTION_TYPES.PARTIAL_UPDATE_TGUSER):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_TGUSER_LIST):
    case FAILURE(ACTION_TYPES.FETCH_TGUSER):
    case FAILURE(ACTION_TYPES.CREATE_TGUSER):
    case FAILURE(ACTION_TYPES.UPDATE_TGUSER):
    case FAILURE(ACTION_TYPES.PARTIAL_UPDATE_TGUSER):
    case FAILURE(ACTION_TYPES.DELETE_TGUSER):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_TGUSER_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_TGUSER):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_TGUSER):
    case SUCCESS(ACTION_TYPES.UPDATE_TGUSER):
    case SUCCESS(ACTION_TYPES.PARTIAL_UPDATE_TGUSER):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_TGUSER):
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

const apiUrl = 'api/tg-users';

// Actions

export const getEntities: ICrudGetAllAction<ITGUser> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_TGUSER_LIST,
  payload: axios.get<ITGUser>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<ITGUser> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_TGUSER,
    payload: axios.get<ITGUser>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<ITGUser> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_TGUSER,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ITGUser> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_TGUSER,
    payload: axios.put(`${apiUrl}/${entity.id}`, cleanEntity(entity)),
  });
  return result;
};

export const partialUpdate: ICrudPutAction<ITGUser> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.PARTIAL_UPDATE_TGUSER,
    payload: axios.patch(`${apiUrl}/${entity.id}`, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<ITGUser> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_TGUSER,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});

export const getTGUserCount: any = () => ({
  type: ACTION_TYPES.FETCH_TGUSER_COUNT,
  payload: axios.get<any>(`${apiUrl}/count`),
});

export const getCountSubscribersByDates: any = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.FETCH_TGUSER_COUNT_BY_DATES,
    payload: axios.post(`${apiUrl}/count/by-dates`, cleanEntity(entity)),
  });
  return result;
};

export const getAllTgUsersForStatistics: any = () => ({
  type: ACTION_TYPES.FETCH_TGUSER_BASE_STATISTICS,
  payload: axios.get<any>(`${apiUrl}/base-statistics`),
});
