import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IAuditChannelsLog, defaultValue } from 'app/shared/model/audit-channels-log.model';

export const ACTION_TYPES = {
  FETCH_AUDITCHANNELSLOG_LIST: 'auditChannelsLog/FETCH_AUDITCHANNELSLOG_LIST',
  FETCH_AUDITCHANNELSLOG: 'auditChannelsLog/FETCH_AUDITCHANNELSLOG',
  CREATE_AUDITCHANNELSLOG: 'auditChannelsLog/CREATE_AUDITCHANNELSLOG',
  UPDATE_AUDITCHANNELSLOG: 'auditChannelsLog/UPDATE_AUDITCHANNELSLOG',
  PARTIAL_UPDATE_AUDITCHANNELSLOG: 'auditChannelsLog/PARTIAL_UPDATE_AUDITCHANNELSLOG',
  DELETE_AUDITCHANNELSLOG: 'auditChannelsLog/DELETE_AUDITCHANNELSLOG',
  RESET: 'auditChannelsLog/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IAuditChannelsLog>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type AuditChannelsLogState = Readonly<typeof initialState>;

// Reducer

export default (state: AuditChannelsLogState = initialState, action): AuditChannelsLogState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_AUDITCHANNELSLOG_LIST):
    case REQUEST(ACTION_TYPES.FETCH_AUDITCHANNELSLOG):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_AUDITCHANNELSLOG):
    case REQUEST(ACTION_TYPES.UPDATE_AUDITCHANNELSLOG):
    case REQUEST(ACTION_TYPES.DELETE_AUDITCHANNELSLOG):
    case REQUEST(ACTION_TYPES.PARTIAL_UPDATE_AUDITCHANNELSLOG):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_AUDITCHANNELSLOG_LIST):
    case FAILURE(ACTION_TYPES.FETCH_AUDITCHANNELSLOG):
    case FAILURE(ACTION_TYPES.CREATE_AUDITCHANNELSLOG):
    case FAILURE(ACTION_TYPES.UPDATE_AUDITCHANNELSLOG):
    case FAILURE(ACTION_TYPES.PARTIAL_UPDATE_AUDITCHANNELSLOG):
    case FAILURE(ACTION_TYPES.DELETE_AUDITCHANNELSLOG):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_AUDITCHANNELSLOG_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_AUDITCHANNELSLOG):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_AUDITCHANNELSLOG):
    case SUCCESS(ACTION_TYPES.UPDATE_AUDITCHANNELSLOG):
    case SUCCESS(ACTION_TYPES.PARTIAL_UPDATE_AUDITCHANNELSLOG):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_AUDITCHANNELSLOG):
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

const apiUrl = 'api/audit-channels-logs';

// Actions

export const getEntities: ICrudGetAllAction<IAuditChannelsLog> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_AUDITCHANNELSLOG_LIST,
  payload: axios.get<IAuditChannelsLog>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<IAuditChannelsLog> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_AUDITCHANNELSLOG,
    payload: axios.get<IAuditChannelsLog>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IAuditChannelsLog> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_AUDITCHANNELSLOG,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IAuditChannelsLog> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_AUDITCHANNELSLOG,
    payload: axios.put(`${apiUrl}/${entity.id}`, cleanEntity(entity)),
  });
  return result;
};

export const partialUpdate: ICrudPutAction<IAuditChannelsLog> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.PARTIAL_UPDATE_AUDITCHANNELSLOG,
    payload: axios.patch(`${apiUrl}/${entity.id}`, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IAuditChannelsLog> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_AUDITCHANNELSLOG,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
