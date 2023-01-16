import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IMessegePannel, defaultValue } from 'app/shared/model/messege-pannel.model';

export const ACTION_TYPES = {
  FETCH_MESSEGEPANNEL_LIST: 'messegePannel/FETCH_MESSEGEPANNEL_LIST',
  FETCH_MESSEGEPANNEL: 'messegePannel/FETCH_MESSEGEPANNEL',
  CREATE_MESSEGEPANNEL: 'messegePannel/CREATE_MESSEGEPANNEL',
  UPDATE_MESSEGEPANNEL: 'messegePannel/UPDATE_MESSEGEPANNEL',
  PARTIAL_UPDATE_MESSEGEPANNEL: 'messegePannel/PARTIAL_UPDATE_MESSEGEPANNEL',
  DELETE_MESSEGEPANNEL: 'messegePannel/DELETE_MESSEGEPANNEL',
  RESET: 'messegePannel/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IMessegePannel>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type MessegePannelState = Readonly<typeof initialState>;

// Reducer

export default (state: MessegePannelState = initialState, action): MessegePannelState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_MESSEGEPANNEL_LIST):
    case REQUEST(ACTION_TYPES.FETCH_MESSEGEPANNEL):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_MESSEGEPANNEL):
    case REQUEST(ACTION_TYPES.UPDATE_MESSEGEPANNEL):
    case REQUEST(ACTION_TYPES.DELETE_MESSEGEPANNEL):
    case REQUEST(ACTION_TYPES.PARTIAL_UPDATE_MESSEGEPANNEL):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_MESSEGEPANNEL_LIST):
    case FAILURE(ACTION_TYPES.FETCH_MESSEGEPANNEL):
    case FAILURE(ACTION_TYPES.CREATE_MESSEGEPANNEL):
    case FAILURE(ACTION_TYPES.UPDATE_MESSEGEPANNEL):
    case FAILURE(ACTION_TYPES.PARTIAL_UPDATE_MESSEGEPANNEL):
    case FAILURE(ACTION_TYPES.DELETE_MESSEGEPANNEL):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_MESSEGEPANNEL_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_MESSEGEPANNEL):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_MESSEGEPANNEL):
    case SUCCESS(ACTION_TYPES.UPDATE_MESSEGEPANNEL):
    case SUCCESS(ACTION_TYPES.PARTIAL_UPDATE_MESSEGEPANNEL):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_MESSEGEPANNEL):
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

const apiUrl = 'api/messege-pannels';

// Actions

export const getEntities: ICrudGetAllAction<IMessegePannel> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_MESSEGEPANNEL_LIST,
  payload: axios.get<IMessegePannel>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<IMessegePannel> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_MESSEGEPANNEL,
    payload: axios.get<IMessegePannel>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IMessegePannel> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_MESSEGEPANNEL,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IMessegePannel> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_MESSEGEPANNEL,
    payload: axios.put(`${apiUrl}/${entity.id}`, cleanEntity(entity)),
  });
  return result;
};

export const partialUpdate: ICrudPutAction<IMessegePannel> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.PARTIAL_UPDATE_MESSEGEPANNEL,
    payload: axios.patch(`${apiUrl}/${entity.id}`, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IMessegePannel> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_MESSEGEPANNEL,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
