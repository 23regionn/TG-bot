import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ISearchTypeLog, defaultValue } from 'app/shared/model/search-type-log.model';

export const ACTION_TYPES = {
  FETCH_SEARCHTYPELOG_LIST: 'searchTypeLog/FETCH_SEARCHTYPELOG_LIST',
  FETCH_SEARCHTYPELOG: 'searchTypeLog/FETCH_SEARCHTYPELOG',
  FETCH_SEARCHTYPELOG_COUNT: 'searchTypeLog/FETCH_SEARCHTYPELOG_COUNT',
  FETCH_SEARCHTYPELOG_COUNT_BY_DATES: 'searchTypeLog/FETCH_SEARCHTYPELOG_COUNT_BY_DATES',
  CREATE_SEARCHTYPELOG: 'searchTypeLog/CREATE_SEARCHTYPELOG',
  UPDATE_SEARCHTYPELOG: 'searchTypeLog/UPDATE_SEARCHTYPELOG',
  PARTIAL_UPDATE_SEARCHTYPELOG: 'searchTypeLog/PARTIAL_UPDATE_SEARCHTYPELOG',
  DELETE_SEARCHTYPELOG: 'searchTypeLog/DELETE_SEARCHTYPELOG',
  RESET: 'searchTypeLog/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ISearchTypeLog>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type SearchTypeLogState = Readonly<typeof initialState>;

// Reducer

export default (state: SearchTypeLogState = initialState, action): SearchTypeLogState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_SEARCHTYPELOG_LIST):
    case REQUEST(ACTION_TYPES.FETCH_SEARCHTYPELOG):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_SEARCHTYPELOG):
    case REQUEST(ACTION_TYPES.UPDATE_SEARCHTYPELOG):
    case REQUEST(ACTION_TYPES.DELETE_SEARCHTYPELOG):
    case REQUEST(ACTION_TYPES.PARTIAL_UPDATE_SEARCHTYPELOG):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_SEARCHTYPELOG_LIST):
    case FAILURE(ACTION_TYPES.FETCH_SEARCHTYPELOG):
    case FAILURE(ACTION_TYPES.CREATE_SEARCHTYPELOG):
    case FAILURE(ACTION_TYPES.UPDATE_SEARCHTYPELOG):
    case FAILURE(ACTION_TYPES.PARTIAL_UPDATE_SEARCHTYPELOG):
    case FAILURE(ACTION_TYPES.DELETE_SEARCHTYPELOG):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_SEARCHTYPELOG_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_SEARCHTYPELOG):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_SEARCHTYPELOG):
    case SUCCESS(ACTION_TYPES.UPDATE_SEARCHTYPELOG):
    case SUCCESS(ACTION_TYPES.PARTIAL_UPDATE_SEARCHTYPELOG):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_SEARCHTYPELOG):
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

const apiUrl = 'api/search-type-logs';

// Actions

export const getEntities: ICrudGetAllAction<ISearchTypeLog> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_SEARCHTYPELOG_LIST,
  payload: axios.get<ISearchTypeLog>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<ISearchTypeLog> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_SEARCHTYPELOG,
    payload: axios.get<ISearchTypeLog>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<ISearchTypeLog> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_SEARCHTYPELOG,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ISearchTypeLog> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_SEARCHTYPELOG,
    payload: axios.put(`${apiUrl}/${entity.id}`, cleanEntity(entity)),
  });
  return result;
};

export const partialUpdate: ICrudPutAction<ISearchTypeLog> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.PARTIAL_UPDATE_SEARCHTYPELOG,
    payload: axios.patch(`${apiUrl}/${entity.id}`, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<ISearchTypeLog> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_SEARCHTYPELOG,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});

export const getAllSearchTypeLogsCount: any = () => ({
  type: ACTION_TYPES.FETCH_SEARCHTYPELOG_COUNT,
  payload: axios.get<any>(`${apiUrl}/count`),
});

export const getSearchTypeByDates: any = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.FETCH_SEARCHTYPELOG_COUNT_BY_DATES,
    payload: axios.post(`${apiUrl}/count/by-dates`, cleanEntity(entity)),
  });
  return result;
};
