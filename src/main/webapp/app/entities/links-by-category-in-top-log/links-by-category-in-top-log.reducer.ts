import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ILinksByCategoryInTopLog, defaultValue } from 'app/shared/model/links-by-category-in-top-log.model';

export const ACTION_TYPES = {
  FETCH_LINKSBYCATEGORYINTOPLOG_LIST: 'linksByCategoryInTopLog/FETCH_LINKSBYCATEGORYINTOPLOG_LIST',
  FETCH_LINKSBYCATEGORYINTOPLOG: 'linksByCategoryInTopLog/FETCH_LINKSBYCATEGORYINTOPLOG',
  CREATE_LINKSBYCATEGORYINTOPLOG: 'linksByCategoryInTopLog/CREATE_LINKSBYCATEGORYINTOPLOG',
  UPDATE_LINKSBYCATEGORYINTOPLOG: 'linksByCategoryInTopLog/UPDATE_LINKSBYCATEGORYINTOPLOG',
  PARTIAL_UPDATE_LINKSBYCATEGORYINTOPLOG: 'linksByCategoryInTopLog/PARTIAL_UPDATE_LINKSBYCATEGORYINTOPLOG',
  DELETE_LINKSBYCATEGORYINTOPLOG: 'linksByCategoryInTopLog/DELETE_LINKSBYCATEGORYINTOPLOG',
  RESET: 'linksByCategoryInTopLog/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ILinksByCategoryInTopLog>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type LinksByCategoryInTopLogState = Readonly<typeof initialState>;

// Reducer

export default (state: LinksByCategoryInTopLogState = initialState, action): LinksByCategoryInTopLogState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_LINKSBYCATEGORYINTOPLOG_LIST):
    case REQUEST(ACTION_TYPES.FETCH_LINKSBYCATEGORYINTOPLOG):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_LINKSBYCATEGORYINTOPLOG):
    case REQUEST(ACTION_TYPES.UPDATE_LINKSBYCATEGORYINTOPLOG):
    case REQUEST(ACTION_TYPES.DELETE_LINKSBYCATEGORYINTOPLOG):
    case REQUEST(ACTION_TYPES.PARTIAL_UPDATE_LINKSBYCATEGORYINTOPLOG):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_LINKSBYCATEGORYINTOPLOG_LIST):
    case FAILURE(ACTION_TYPES.FETCH_LINKSBYCATEGORYINTOPLOG):
    case FAILURE(ACTION_TYPES.CREATE_LINKSBYCATEGORYINTOPLOG):
    case FAILURE(ACTION_TYPES.UPDATE_LINKSBYCATEGORYINTOPLOG):
    case FAILURE(ACTION_TYPES.PARTIAL_UPDATE_LINKSBYCATEGORYINTOPLOG):
    case FAILURE(ACTION_TYPES.DELETE_LINKSBYCATEGORYINTOPLOG):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_LINKSBYCATEGORYINTOPLOG_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_LINKSBYCATEGORYINTOPLOG):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_LINKSBYCATEGORYINTOPLOG):
    case SUCCESS(ACTION_TYPES.UPDATE_LINKSBYCATEGORYINTOPLOG):
    case SUCCESS(ACTION_TYPES.PARTIAL_UPDATE_LINKSBYCATEGORYINTOPLOG):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_LINKSBYCATEGORYINTOPLOG):
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

const apiUrl = 'api/links-by-category-in-top-logs';

// Actions

export const getEntities: ICrudGetAllAction<ILinksByCategoryInTopLog> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_LINKSBYCATEGORYINTOPLOG_LIST,
  payload: axios.get<ILinksByCategoryInTopLog>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<ILinksByCategoryInTopLog> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_LINKSBYCATEGORYINTOPLOG,
    payload: axios.get<ILinksByCategoryInTopLog>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<ILinksByCategoryInTopLog> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_LINKSBYCATEGORYINTOPLOG,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ILinksByCategoryInTopLog> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_LINKSBYCATEGORYINTOPLOG,
    payload: axios.put(`${apiUrl}/${entity.id}`, cleanEntity(entity)),
  });
  return result;
};

export const partialUpdate: ICrudPutAction<ILinksByCategoryInTopLog> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.PARTIAL_UPDATE_LINKSBYCATEGORYINTOPLOG,
    payload: axios.patch(`${apiUrl}/${entity.id}`, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<ILinksByCategoryInTopLog> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_LINKSBYCATEGORYINTOPLOG,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
