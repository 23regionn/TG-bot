import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ILinksByCategoryInTop, defaultValue } from 'app/shared/model/links-by-category-in-top.model';

export const ACTION_TYPES = {
  FETCH_LINKSBYCATEGORYINTOP_LIST: 'linksByCategoryInTop/FETCH_LINKSBYCATEGORYINTOP_LIST',
  FETCH_LINKSBYCATEGORYINTOP: 'linksByCategoryInTop/FETCH_LINKSBYCATEGORYINTOP',
  CREATE_LINKSBYCATEGORYINTOP: 'linksByCategoryInTop/CREATE_LINKSBYCATEGORYINTOP',
  UPDATE_LINKSBYCATEGORYINTOP: 'linksByCategoryInTop/UPDATE_LINKSBYCATEGORYINTOP',
  PARTIAL_UPDATE_LINKSBYCATEGORYINTOP: 'linksByCategoryInTop/PARTIAL_UPDATE_LINKSBYCATEGORYINTOP',
  DELETE_LINKSBYCATEGORYINTOP: 'linksByCategoryInTop/DELETE_LINKSBYCATEGORYINTOP',
  RESET: 'linksByCategoryInTop/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ILinksByCategoryInTop>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type LinksByCategoryInTopState = Readonly<typeof initialState>;

// Reducer

export default (state: LinksByCategoryInTopState = initialState, action): LinksByCategoryInTopState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_LINKSBYCATEGORYINTOP_LIST):
    case REQUEST(ACTION_TYPES.FETCH_LINKSBYCATEGORYINTOP):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_LINKSBYCATEGORYINTOP):
    case REQUEST(ACTION_TYPES.UPDATE_LINKSBYCATEGORYINTOP):
    case REQUEST(ACTION_TYPES.DELETE_LINKSBYCATEGORYINTOP):
    case REQUEST(ACTION_TYPES.PARTIAL_UPDATE_LINKSBYCATEGORYINTOP):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_LINKSBYCATEGORYINTOP_LIST):
    case FAILURE(ACTION_TYPES.FETCH_LINKSBYCATEGORYINTOP):
    case FAILURE(ACTION_TYPES.CREATE_LINKSBYCATEGORYINTOP):
    case FAILURE(ACTION_TYPES.UPDATE_LINKSBYCATEGORYINTOP):
    case FAILURE(ACTION_TYPES.PARTIAL_UPDATE_LINKSBYCATEGORYINTOP):
    case FAILURE(ACTION_TYPES.DELETE_LINKSBYCATEGORYINTOP):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_LINKSBYCATEGORYINTOP_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_LINKSBYCATEGORYINTOP):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_LINKSBYCATEGORYINTOP):
    case SUCCESS(ACTION_TYPES.UPDATE_LINKSBYCATEGORYINTOP):
    case SUCCESS(ACTION_TYPES.PARTIAL_UPDATE_LINKSBYCATEGORYINTOP):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_LINKSBYCATEGORYINTOP):
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

const apiUrl = 'api/links-by-category-in-tops';

// Actions

export const getEntities: ICrudGetAllAction<ILinksByCategoryInTop> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_LINKSBYCATEGORYINTOP_LIST,
  payload: axios.get<ILinksByCategoryInTop>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<ILinksByCategoryInTop> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_LINKSBYCATEGORYINTOP,
    payload: axios.get<ILinksByCategoryInTop>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<ILinksByCategoryInTop> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_LINKSBYCATEGORYINTOP,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ILinksByCategoryInTop> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_LINKSBYCATEGORYINTOP,
    payload: axios.put(`${apiUrl}/${entity.id}`, cleanEntity(entity)),
  });
  return result;
};

export const partialUpdate: ICrudPutAction<ILinksByCategoryInTop> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.PARTIAL_UPDATE_LINKSBYCATEGORYINTOP,
    payload: axios.patch(`${apiUrl}/${entity.id}`, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<ILinksByCategoryInTop> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_LINKSBYCATEGORYINTOP,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
