import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IShowChannelsInCityLog, defaultValue } from 'app/shared/model/show-channels-in-city-log.model';

export const ACTION_TYPES = {
  FETCH_SHOWCHANNELSINCITYLOG_LIST: 'showChannelsInCityLog/FETCH_SHOWCHANNELSINCITYLOG_LIST',
  FETCH_SHOWCHANNELSINCITYLOG: 'showChannelsInCityLog/FETCH_SHOWCHANNELSINCITYLOG',
  CREATE_SHOWCHANNELSINCITYLOG: 'showChannelsInCityLog/CREATE_SHOWCHANNELSINCITYLOG',
  UPDATE_SHOWCHANNELSINCITYLOG: 'showChannelsInCityLog/UPDATE_SHOWCHANNELSINCITYLOG',
  PARTIAL_UPDATE_SHOWCHANNELSINCITYLOG: 'showChannelsInCityLog/PARTIAL_UPDATE_SHOWCHANNELSINCITYLOG',
  DELETE_SHOWCHANNELSINCITYLOG: 'showChannelsInCityLog/DELETE_SHOWCHANNELSINCITYLOG',
  RESET: 'showChannelsInCityLog/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IShowChannelsInCityLog>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type ShowChannelsInCityLogState = Readonly<typeof initialState>;

// Reducer

export default (state: ShowChannelsInCityLogState = initialState, action): ShowChannelsInCityLogState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_SHOWCHANNELSINCITYLOG_LIST):
    case REQUEST(ACTION_TYPES.FETCH_SHOWCHANNELSINCITYLOG):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_SHOWCHANNELSINCITYLOG):
    case REQUEST(ACTION_TYPES.UPDATE_SHOWCHANNELSINCITYLOG):
    case REQUEST(ACTION_TYPES.DELETE_SHOWCHANNELSINCITYLOG):
    case REQUEST(ACTION_TYPES.PARTIAL_UPDATE_SHOWCHANNELSINCITYLOG):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_SHOWCHANNELSINCITYLOG_LIST):
    case FAILURE(ACTION_TYPES.FETCH_SHOWCHANNELSINCITYLOG):
    case FAILURE(ACTION_TYPES.CREATE_SHOWCHANNELSINCITYLOG):
    case FAILURE(ACTION_TYPES.UPDATE_SHOWCHANNELSINCITYLOG):
    case FAILURE(ACTION_TYPES.PARTIAL_UPDATE_SHOWCHANNELSINCITYLOG):
    case FAILURE(ACTION_TYPES.DELETE_SHOWCHANNELSINCITYLOG):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_SHOWCHANNELSINCITYLOG_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_SHOWCHANNELSINCITYLOG):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_SHOWCHANNELSINCITYLOG):
    case SUCCESS(ACTION_TYPES.UPDATE_SHOWCHANNELSINCITYLOG):
    case SUCCESS(ACTION_TYPES.PARTIAL_UPDATE_SHOWCHANNELSINCITYLOG):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_SHOWCHANNELSINCITYLOG):
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

const apiUrl = 'api/show-channels-in-city-logs';

// Actions

export const getEntities: ICrudGetAllAction<IShowChannelsInCityLog> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_SHOWCHANNELSINCITYLOG_LIST,
  payload: axios.get<IShowChannelsInCityLog>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<IShowChannelsInCityLog> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_SHOWCHANNELSINCITYLOG,
    payload: axios.get<IShowChannelsInCityLog>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IShowChannelsInCityLog> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_SHOWCHANNELSINCITYLOG,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IShowChannelsInCityLog> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_SHOWCHANNELSINCITYLOG,
    payload: axios.put(`${apiUrl}/${entity.id}`, cleanEntity(entity)),
  });
  return result;
};

export const partialUpdate: ICrudPutAction<IShowChannelsInCityLog> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.PARTIAL_UPDATE_SHOWCHANNELSINCITYLOG,
    payload: axios.patch(`${apiUrl}/${entity.id}`, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IShowChannelsInCityLog> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_SHOWCHANNELSINCITYLOG,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
