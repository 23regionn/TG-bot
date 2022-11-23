import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IOfferFromCostumers, defaultValue } from 'app/shared/model/offer-from-costumers.model';

export const ACTION_TYPES = {
  FETCH_OFFERFROMCOSTUMERS_LIST: 'offerFromCostumers/FETCH_OFFERFROMCOSTUMERS_LIST',
  FETCH_OFFERFROMCOSTUMERS: 'offerFromCostumers/FETCH_OFFERFROMCOSTUMERS',
  CREATE_OFFERFROMCOSTUMERS: 'offerFromCostumers/CREATE_OFFERFROMCOSTUMERS',
  UPDATE_OFFERFROMCOSTUMERS: 'offerFromCostumers/UPDATE_OFFERFROMCOSTUMERS',
  PARTIAL_UPDATE_OFFERFROMCOSTUMERS: 'offerFromCostumers/PARTIAL_UPDATE_OFFERFROMCOSTUMERS',
  DELETE_OFFERFROMCOSTUMERS: 'offerFromCostumers/DELETE_OFFERFROMCOSTUMERS',
  RESET: 'offerFromCostumers/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IOfferFromCostumers>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type OfferFromCostumersState = Readonly<typeof initialState>;

// Reducer

export default (state: OfferFromCostumersState = initialState, action): OfferFromCostumersState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_OFFERFROMCOSTUMERS_LIST):
    case REQUEST(ACTION_TYPES.FETCH_OFFERFROMCOSTUMERS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_OFFERFROMCOSTUMERS):
    case REQUEST(ACTION_TYPES.UPDATE_OFFERFROMCOSTUMERS):
    case REQUEST(ACTION_TYPES.DELETE_OFFERFROMCOSTUMERS):
    case REQUEST(ACTION_TYPES.PARTIAL_UPDATE_OFFERFROMCOSTUMERS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_OFFERFROMCOSTUMERS_LIST):
    case FAILURE(ACTION_TYPES.FETCH_OFFERFROMCOSTUMERS):
    case FAILURE(ACTION_TYPES.CREATE_OFFERFROMCOSTUMERS):
    case FAILURE(ACTION_TYPES.UPDATE_OFFERFROMCOSTUMERS):
    case FAILURE(ACTION_TYPES.PARTIAL_UPDATE_OFFERFROMCOSTUMERS):
    case FAILURE(ACTION_TYPES.DELETE_OFFERFROMCOSTUMERS):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_OFFERFROMCOSTUMERS_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_OFFERFROMCOSTUMERS):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_OFFERFROMCOSTUMERS):
    case SUCCESS(ACTION_TYPES.UPDATE_OFFERFROMCOSTUMERS):
    case SUCCESS(ACTION_TYPES.PARTIAL_UPDATE_OFFERFROMCOSTUMERS):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_OFFERFROMCOSTUMERS):
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

const apiUrl = 'api/offer-from-costumers';

// Actions

export const getEntities: ICrudGetAllAction<IOfferFromCostumers> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_OFFERFROMCOSTUMERS_LIST,
  payload: axios.get<IOfferFromCostumers>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<IOfferFromCostumers> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_OFFERFROMCOSTUMERS,
    payload: axios.get<IOfferFromCostumers>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IOfferFromCostumers> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_OFFERFROMCOSTUMERS,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IOfferFromCostumers> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_OFFERFROMCOSTUMERS,
    payload: axios.put(`${apiUrl}/${entity.id}`, cleanEntity(entity)),
  });
  return result;
};

export const partialUpdate: ICrudPutAction<IOfferFromCostumers> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.PARTIAL_UPDATE_OFFERFROMCOSTUMERS,
    payload: axios.patch(`${apiUrl}/${entity.id}`, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IOfferFromCostumers> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_OFFERFROMCOSTUMERS,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
