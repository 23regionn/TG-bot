import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ITGUserLog, defaultValue } from 'app/shared/model/tg-user-log.model';

export const ACTION_TYPES = {
  FETCH_TGUSERLOG_LIST: 'tGUserLog/FETCH_TGUSERLOG_LIST',
  FETCH_TGUSERLOG: 'tGUserLog/FETCH_TGUSERLOG',
  CREATE_TGUSERLOG: 'tGUserLog/CREATE_TGUSERLOG',
  UPDATE_TGUSERLOG: 'tGUserLog/UPDATE_TGUSERLOG',
  PARTIAL_UPDATE_TGUSERLOG: 'tGUserLog/PARTIAL_UPDATE_TGUSERLOG',
  DELETE_TGUSERLOG: 'tGUserLog/DELETE_TGUSERLOG',
  RESET: 'tGUserLog/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ITGUserLog>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type TGUserLogState = Readonly<typeof initialState>;

// Reducer

export default (state: TGUserLogState = initialState, action): TGUserLogState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_TGUSERLOG_LIST):
    case REQUEST(ACTION_TYPES.FETCH_TGUSERLOG):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_TGUSERLOG):
    case REQUEST(ACTION_TYPES.UPDATE_TGUSERLOG):
    case REQUEST(ACTION_TYPES.DELETE_TGUSERLOG):
    case REQUEST(ACTION_TYPES.PARTIAL_UPDATE_TGUSERLOG):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_TGUSERLOG_LIST):
    case FAILURE(ACTION_TYPES.FETCH_TGUSERLOG):
    case FAILURE(ACTION_TYPES.CREATE_TGUSERLOG):
    case FAILURE(ACTION_TYPES.UPDATE_TGUSERLOG):
    case FAILURE(ACTION_TYPES.PARTIAL_UPDATE_TGUSERLOG):
    case FAILURE(ACTION_TYPES.DELETE_TGUSERLOG):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_TGUSERLOG_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_TGUSERLOG):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_TGUSERLOG):
    case SUCCESS(ACTION_TYPES.UPDATE_TGUSERLOG):
    case SUCCESS(ACTION_TYPES.PARTIAL_UPDATE_TGUSERLOG):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_TGUSERLOG):
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

const apiUrl = 'api/tg-user-logs';

// Actions

export const getEntities: ICrudGetAllAction<ITGUserLog> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_TGUSERLOG_LIST,
  payload: axios.get<ITGUserLog>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<ITGUserLog> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_TGUSERLOG,
    payload: axios.get<ITGUserLog>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<ITGUserLog> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_TGUSERLOG,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ITGUserLog> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_TGUSERLOG,
    payload: axios.put(`${apiUrl}/${entity.id}`, cleanEntity(entity)),
  });
  return result;
};

export const partialUpdate: ICrudPutAction<ITGUserLog> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.PARTIAL_UPDATE_TGUSERLOG,
    payload: axios.patch(`${apiUrl}/${entity.id}`, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<ITGUserLog> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_TGUSERLOG,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
