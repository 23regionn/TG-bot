import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IShowChannelsInCategoryLog, defaultValue } from 'app/shared/model/show-channels-in-category-log.model';

export const ACTION_TYPES = {
  FETCH_SHOWCHANNELSINCATEGORYLOG_LIST: 'showChannelsInCategoryLog/FETCH_SHOWCHANNELSINCATEGORYLOG_LIST',
  FETCH_SHOWCHANNELSINCATEGORYLOG: 'showChannelsInCategoryLog/FETCH_SHOWCHANNELSINCATEGORYLOG',
  CREATE_SHOWCHANNELSINCATEGORYLOG: 'showChannelsInCategoryLog/CREATE_SHOWCHANNELSINCATEGORYLOG',
  UPDATE_SHOWCHANNELSINCATEGORYLOG: 'showChannelsInCategoryLog/UPDATE_SHOWCHANNELSINCATEGORYLOG',
  PARTIAL_UPDATE_SHOWCHANNELSINCATEGORYLOG: 'showChannelsInCategoryLog/PARTIAL_UPDATE_SHOWCHANNELSINCATEGORYLOG',
  DELETE_SHOWCHANNELSINCATEGORYLOG: 'showChannelsInCategoryLog/DELETE_SHOWCHANNELSINCATEGORYLOG',
  RESET: 'showChannelsInCategoryLog/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IShowChannelsInCategoryLog>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type ShowChannelsInCategoryLogState = Readonly<typeof initialState>;

// Reducer

export default (state: ShowChannelsInCategoryLogState = initialState, action): ShowChannelsInCategoryLogState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_SHOWCHANNELSINCATEGORYLOG_LIST):
    case REQUEST(ACTION_TYPES.FETCH_SHOWCHANNELSINCATEGORYLOG):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_SHOWCHANNELSINCATEGORYLOG):
    case REQUEST(ACTION_TYPES.UPDATE_SHOWCHANNELSINCATEGORYLOG):
    case REQUEST(ACTION_TYPES.DELETE_SHOWCHANNELSINCATEGORYLOG):
    case REQUEST(ACTION_TYPES.PARTIAL_UPDATE_SHOWCHANNELSINCATEGORYLOG):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_SHOWCHANNELSINCATEGORYLOG_LIST):
    case FAILURE(ACTION_TYPES.FETCH_SHOWCHANNELSINCATEGORYLOG):
    case FAILURE(ACTION_TYPES.CREATE_SHOWCHANNELSINCATEGORYLOG):
    case FAILURE(ACTION_TYPES.UPDATE_SHOWCHANNELSINCATEGORYLOG):
    case FAILURE(ACTION_TYPES.PARTIAL_UPDATE_SHOWCHANNELSINCATEGORYLOG):
    case FAILURE(ACTION_TYPES.DELETE_SHOWCHANNELSINCATEGORYLOG):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_SHOWCHANNELSINCATEGORYLOG_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_SHOWCHANNELSINCATEGORYLOG):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_SHOWCHANNELSINCATEGORYLOG):
    case SUCCESS(ACTION_TYPES.UPDATE_SHOWCHANNELSINCATEGORYLOG):
    case SUCCESS(ACTION_TYPES.PARTIAL_UPDATE_SHOWCHANNELSINCATEGORYLOG):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_SHOWCHANNELSINCATEGORYLOG):
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

const apiUrl = 'api/show-channels-in-category-logs';

// Actions

export const getEntities: ICrudGetAllAction<IShowChannelsInCategoryLog> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_SHOWCHANNELSINCATEGORYLOG_LIST,
  payload: axios.get<IShowChannelsInCategoryLog>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<IShowChannelsInCategoryLog> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_SHOWCHANNELSINCATEGORYLOG,
    payload: axios.get<IShowChannelsInCategoryLog>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IShowChannelsInCategoryLog> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_SHOWCHANNELSINCATEGORYLOG,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IShowChannelsInCategoryLog> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_SHOWCHANNELSINCATEGORYLOG,
    payload: axios.put(`${apiUrl}/${entity.id}`, cleanEntity(entity)),
  });
  return result;
};

export const partialUpdate: ICrudPutAction<IShowChannelsInCategoryLog> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.PARTIAL_UPDATE_SHOWCHANNELSINCATEGORYLOG,
    payload: axios.patch(`${apiUrl}/${entity.id}`, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IShowChannelsInCategoryLog> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_SHOWCHANNELSINCATEGORYLOG,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
