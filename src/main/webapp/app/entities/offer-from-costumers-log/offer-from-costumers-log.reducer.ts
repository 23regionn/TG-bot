import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IOfferFromCostumersLog, defaultValue } from 'app/shared/model/offer-from-costumers-log.model';

export const ACTION_TYPES = {
  FETCH_OFFERFROMCOSTUMERSLOG_LIST: 'offerFromCostumersLog/FETCH_OFFERFROMCOSTUMERSLOG_LIST',
  FETCH_OFFERFROMCOSTUMERSLOG: 'offerFromCostumersLog/FETCH_OFFERFROMCOSTUMERSLOG',
  CREATE_OFFERFROMCOSTUMERSLOG: 'offerFromCostumersLog/CREATE_OFFERFROMCOSTUMERSLOG',
  UPDATE_OFFERFROMCOSTUMERSLOG: 'offerFromCostumersLog/UPDATE_OFFERFROMCOSTUMERSLOG',
  PARTIAL_UPDATE_OFFERFROMCOSTUMERSLOG: 'offerFromCostumersLog/PARTIAL_UPDATE_OFFERFROMCOSTUMERSLOG',
  DELETE_OFFERFROMCOSTUMERSLOG: 'offerFromCostumersLog/DELETE_OFFERFROMCOSTUMERSLOG',
  RESET: 'offerFromCostumersLog/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IOfferFromCostumersLog>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type OfferFromCostumersLogState = Readonly<typeof initialState>;

// Reducer

export default (state: OfferFromCostumersLogState = initialState, action): OfferFromCostumersLogState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_OFFERFROMCOSTUMERSLOG_LIST):
    case REQUEST(ACTION_TYPES.FETCH_OFFERFROMCOSTUMERSLOG):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_OFFERFROMCOSTUMERSLOG):
    case REQUEST(ACTION_TYPES.UPDATE_OFFERFROMCOSTUMERSLOG):
    case REQUEST(ACTION_TYPES.DELETE_OFFERFROMCOSTUMERSLOG):
    case REQUEST(ACTION_TYPES.PARTIAL_UPDATE_OFFERFROMCOSTUMERSLOG):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_OFFERFROMCOSTUMERSLOG_LIST):
    case FAILURE(ACTION_TYPES.FETCH_OFFERFROMCOSTUMERSLOG):
    case FAILURE(ACTION_TYPES.CREATE_OFFERFROMCOSTUMERSLOG):
    case FAILURE(ACTION_TYPES.UPDATE_OFFERFROMCOSTUMERSLOG):
    case FAILURE(ACTION_TYPES.PARTIAL_UPDATE_OFFERFROMCOSTUMERSLOG):
    case FAILURE(ACTION_TYPES.DELETE_OFFERFROMCOSTUMERSLOG):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_OFFERFROMCOSTUMERSLOG_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_OFFERFROMCOSTUMERSLOG):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_OFFERFROMCOSTUMERSLOG):
    case SUCCESS(ACTION_TYPES.UPDATE_OFFERFROMCOSTUMERSLOG):
    case SUCCESS(ACTION_TYPES.PARTIAL_UPDATE_OFFERFROMCOSTUMERSLOG):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_OFFERFROMCOSTUMERSLOG):
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

const apiUrl = 'api/offer-from-costumers-logs';

// Actions

export const getEntities: ICrudGetAllAction<IOfferFromCostumersLog> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_OFFERFROMCOSTUMERSLOG_LIST,
  payload: axios.get<IOfferFromCostumersLog>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<IOfferFromCostumersLog> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_OFFERFROMCOSTUMERSLOG,
    payload: axios.get<IOfferFromCostumersLog>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IOfferFromCostumersLog> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_OFFERFROMCOSTUMERSLOG,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IOfferFromCostumersLog> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_OFFERFROMCOSTUMERSLOG,
    payload: axios.put(`${apiUrl}/${entity.id}`, cleanEntity(entity)),
  });
  return result;
};

export const partialUpdate: ICrudPutAction<IOfferFromCostumersLog> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.PARTIAL_UPDATE_OFFERFROMCOSTUMERSLOG,
    payload: axios.patch(`${apiUrl}/${entity.id}`, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IOfferFromCostumersLog> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_OFFERFROMCOSTUMERSLOG,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
