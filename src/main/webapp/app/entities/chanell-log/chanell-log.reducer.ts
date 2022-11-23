import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IChanellLog, defaultValue } from 'app/shared/model/chanell-log.model';

export const ACTION_TYPES = {
  FETCH_CHANELLLOG_LIST: 'chanellLog/FETCH_CHANELLLOG_LIST',
  FETCH_CHANELLLOG: 'chanellLog/FETCH_CHANELLLOG',
  CREATE_CHANELLLOG: 'chanellLog/CREATE_CHANELLLOG',
  UPDATE_CHANELLLOG: 'chanellLog/UPDATE_CHANELLLOG',
  PARTIAL_UPDATE_CHANELLLOG: 'chanellLog/PARTIAL_UPDATE_CHANELLLOG',
  DELETE_CHANELLLOG: 'chanellLog/DELETE_CHANELLLOG',
  RESET: 'chanellLog/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IChanellLog>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type ChanellLogState = Readonly<typeof initialState>;

// Reducer

export default (state: ChanellLogState = initialState, action): ChanellLogState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_CHANELLLOG_LIST):
    case REQUEST(ACTION_TYPES.FETCH_CHANELLLOG):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_CHANELLLOG):
    case REQUEST(ACTION_TYPES.UPDATE_CHANELLLOG):
    case REQUEST(ACTION_TYPES.DELETE_CHANELLLOG):
    case REQUEST(ACTION_TYPES.PARTIAL_UPDATE_CHANELLLOG):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_CHANELLLOG_LIST):
    case FAILURE(ACTION_TYPES.FETCH_CHANELLLOG):
    case FAILURE(ACTION_TYPES.CREATE_CHANELLLOG):
    case FAILURE(ACTION_TYPES.UPDATE_CHANELLLOG):
    case FAILURE(ACTION_TYPES.PARTIAL_UPDATE_CHANELLLOG):
    case FAILURE(ACTION_TYPES.DELETE_CHANELLLOG):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_CHANELLLOG_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_CHANELLLOG):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_CHANELLLOG):
    case SUCCESS(ACTION_TYPES.UPDATE_CHANELLLOG):
    case SUCCESS(ACTION_TYPES.PARTIAL_UPDATE_CHANELLLOG):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_CHANELLLOG):
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

const apiUrl = 'api/chanell-logs';

// Actions

export const getEntities: ICrudGetAllAction<IChanellLog> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_CHANELLLOG_LIST,
  payload: axios.get<IChanellLog>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<IChanellLog> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_CHANELLLOG,
    payload: axios.get<IChanellLog>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IChanellLog> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_CHANELLLOG,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IChanellLog> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_CHANELLLOG,
    payload: axios.put(`${apiUrl}/${entity.id}`, cleanEntity(entity)),
  });
  return result;
};

export const partialUpdate: ICrudPutAction<IChanellLog> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.PARTIAL_UPDATE_CHANELLLOG,
    payload: axios.patch(`${apiUrl}/${entity.id}`, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IChanellLog> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_CHANELLLOG,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
