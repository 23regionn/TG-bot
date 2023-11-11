import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ICategoryLog, defaultValue } from 'app/shared/model/category-log.model';

export const ACTION_TYPES = {
  FETCH_CATEGORYLOG_LIST: 'categoryLog/FETCH_CATEGORYLOG_LIST',
  FETCH_CATEGORYLOG_LIST_FOR_CITY: 'categoryLog/FETCH_CATEGORYLOG_LIST_FOR_CITY',
  FETCH_CATEGORYLOG_LIST_FOR_CITY_BY_DATES: 'categoryLog/FETCH_CATEGORYLOG_LIST_FOR_CITY_BY_DATES',
  FETCH_CATEGORYLOG: 'categoryLog/FETCH_CATEGORYLOG',
  CREATE_CATEGORYLOG: 'categoryLog/CREATE_CATEGORYLOG',
  UPDATE_CATEGORYLOG: 'categoryLog/UPDATE_CATEGORYLOG',
  PARTIAL_UPDATE_CATEGORYLOG: 'categoryLog/PARTIAL_UPDATE_CATEGORYLOG',
  DELETE_CATEGORYLOG: 'categoryLog/DELETE_CATEGORYLOG',
  RESET: 'categoryLog/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ICategoryLog>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type CategoryLogState = Readonly<typeof initialState>;

// Reducer

export default (state: CategoryLogState = initialState, action): CategoryLogState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_CATEGORYLOG_LIST):
    case REQUEST(ACTION_TYPES.FETCH_CATEGORYLOG):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_CATEGORYLOG):
    case REQUEST(ACTION_TYPES.UPDATE_CATEGORYLOG):
    case REQUEST(ACTION_TYPES.DELETE_CATEGORYLOG):
    case REQUEST(ACTION_TYPES.PARTIAL_UPDATE_CATEGORYLOG):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_CATEGORYLOG_LIST):
    case FAILURE(ACTION_TYPES.FETCH_CATEGORYLOG):
    case FAILURE(ACTION_TYPES.CREATE_CATEGORYLOG):
    case FAILURE(ACTION_TYPES.UPDATE_CATEGORYLOG):
    case FAILURE(ACTION_TYPES.PARTIAL_UPDATE_CATEGORYLOG):
    case FAILURE(ACTION_TYPES.DELETE_CATEGORYLOG):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_CATEGORYLOG_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_CATEGORYLOG):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_CATEGORYLOG):
    case SUCCESS(ACTION_TYPES.UPDATE_CATEGORYLOG):
    case SUCCESS(ACTION_TYPES.PARTIAL_UPDATE_CATEGORYLOG):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_CATEGORYLOG):
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

const apiUrl = 'api/category-logs';

// Actions

export const getEntities: ICrudGetAllAction<ICategoryLog> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_CATEGORYLOG_LIST,
  payload: axios.get<ICategoryLog>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<ICategoryLog> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_CATEGORYLOG,
    payload: axios.get<ICategoryLog>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<ICategoryLog> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_CATEGORYLOG,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ICategoryLog> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_CATEGORYLOG,
    payload: axios.put(`${apiUrl}/${entity.id}`, cleanEntity(entity)),
  });
  return result;
};

export const partialUpdate: ICrudPutAction<ICategoryLog> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.PARTIAL_UPDATE_CATEGORYLOG,
    payload: axios.patch(`${apiUrl}/${entity.id}`, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<ICategoryLog> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_CATEGORYLOG,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});

export const getCategoriesStatistics: any = () => ({
  type: ACTION_TYPES.FETCH_CATEGORYLOG_LIST,
  payload: axios.get<any>(`${apiUrl}-statistics`),
});

export const getStatisticsCategoryLogByDates: any = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_CATEGORYLOG,
    payload: axios.post(`${apiUrl}-statistics-by-dates`, cleanEntity(entity)),
  });
  return result;
};

export const getCategoriesStatisticsForCity: any = idCity => ({
  type: ACTION_TYPES.FETCH_CATEGORYLOG_LIST_FOR_CITY,
  payload: axios.get<any>(`${apiUrl}-statistics/for-city/${idCity}`),
});

export const getStatisticsCategoryLogForCityByDates: any = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.FETCH_CATEGORYLOG_LIST_FOR_CITY_BY_DATES,
    payload: axios.post(`${apiUrl}-statistics-by-dates/for-city`, cleanEntity(entity)),
  });
  return result;
};
