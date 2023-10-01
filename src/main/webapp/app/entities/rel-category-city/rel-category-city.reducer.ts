import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IRelCategoryCity, defaultValue } from 'app/shared/model/rel-category-city.model';

export const ACTION_TYPES = {
  FETCH_RELCATEGORYCITY_LIST: 'relCategoryCity/FETCH_RELCATEGORYCITY_LIST',
  FETCH_RELCATEGORYCITY: 'relCategoryCity/FETCH_RELCATEGORYCITY',
  CREATE_RELCATEGORYCITY: 'relCategoryCity/CREATE_RELCATEGORYCITY',
  UPDATE_RELCATEGORYCITY: 'relCategoryCity/UPDATE_RELCATEGORYCITY',
  PARTIAL_UPDATE_RELCATEGORYCITY: 'relCategoryCity/PARTIAL_UPDATE_RELCATEGORYCITY',
  DELETE_RELCATEGORYCITY: 'relCategoryCity/DELETE_RELCATEGORYCITY',
  RESET: 'relCategoryCity/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IRelCategoryCity>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type RelCategoryCityState = Readonly<typeof initialState>;

// Reducer

export default (state: RelCategoryCityState = initialState, action): RelCategoryCityState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_RELCATEGORYCITY_LIST):
    case REQUEST(ACTION_TYPES.FETCH_RELCATEGORYCITY):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_RELCATEGORYCITY):
    case REQUEST(ACTION_TYPES.UPDATE_RELCATEGORYCITY):
    case REQUEST(ACTION_TYPES.DELETE_RELCATEGORYCITY):
    case REQUEST(ACTION_TYPES.PARTIAL_UPDATE_RELCATEGORYCITY):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_RELCATEGORYCITY_LIST):
    case FAILURE(ACTION_TYPES.FETCH_RELCATEGORYCITY):
    case FAILURE(ACTION_TYPES.CREATE_RELCATEGORYCITY):
    case FAILURE(ACTION_TYPES.UPDATE_RELCATEGORYCITY):
    case FAILURE(ACTION_TYPES.PARTIAL_UPDATE_RELCATEGORYCITY):
    case FAILURE(ACTION_TYPES.DELETE_RELCATEGORYCITY):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_RELCATEGORYCITY_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_RELCATEGORYCITY):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_RELCATEGORYCITY):
    case SUCCESS(ACTION_TYPES.UPDATE_RELCATEGORYCITY):
    case SUCCESS(ACTION_TYPES.PARTIAL_UPDATE_RELCATEGORYCITY):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_RELCATEGORYCITY):
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

const apiUrl = 'api/rel-category-cities';

// Actions

export const getEntities: ICrudGetAllAction<IRelCategoryCity> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_RELCATEGORYCITY_LIST,
  payload: axios.get<IRelCategoryCity>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<IRelCategoryCity> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_RELCATEGORYCITY,
    payload: axios.get<IRelCategoryCity>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IRelCategoryCity> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_RELCATEGORYCITY,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IRelCategoryCity> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_RELCATEGORYCITY,
    payload: axios.put(`${apiUrl}/${entity.id}`, cleanEntity(entity)),
  });
  return result;
};

export const partialUpdate: ICrudPutAction<IRelCategoryCity> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.PARTIAL_UPDATE_RELCATEGORYCITY,
    payload: axios.patch(`${apiUrl}/${entity.id}`, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IRelCategoryCity> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_RELCATEGORYCITY,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
