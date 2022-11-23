import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IMembersTradeDealLog, defaultValue } from 'app/shared/model/members-trade-deal-log.model';

export const ACTION_TYPES = {
  FETCH_MEMBERSTRADEDEALLOG_LIST: 'membersTradeDealLog/FETCH_MEMBERSTRADEDEALLOG_LIST',
  FETCH_MEMBERSTRADEDEALLOG: 'membersTradeDealLog/FETCH_MEMBERSTRADEDEALLOG',
  CREATE_MEMBERSTRADEDEALLOG: 'membersTradeDealLog/CREATE_MEMBERSTRADEDEALLOG',
  UPDATE_MEMBERSTRADEDEALLOG: 'membersTradeDealLog/UPDATE_MEMBERSTRADEDEALLOG',
  PARTIAL_UPDATE_MEMBERSTRADEDEALLOG: 'membersTradeDealLog/PARTIAL_UPDATE_MEMBERSTRADEDEALLOG',
  DELETE_MEMBERSTRADEDEALLOG: 'membersTradeDealLog/DELETE_MEMBERSTRADEDEALLOG',
  RESET: 'membersTradeDealLog/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IMembersTradeDealLog>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type MembersTradeDealLogState = Readonly<typeof initialState>;

// Reducer

export default (state: MembersTradeDealLogState = initialState, action): MembersTradeDealLogState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_MEMBERSTRADEDEALLOG_LIST):
    case REQUEST(ACTION_TYPES.FETCH_MEMBERSTRADEDEALLOG):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_MEMBERSTRADEDEALLOG):
    case REQUEST(ACTION_TYPES.UPDATE_MEMBERSTRADEDEALLOG):
    case REQUEST(ACTION_TYPES.DELETE_MEMBERSTRADEDEALLOG):
    case REQUEST(ACTION_TYPES.PARTIAL_UPDATE_MEMBERSTRADEDEALLOG):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_MEMBERSTRADEDEALLOG_LIST):
    case FAILURE(ACTION_TYPES.FETCH_MEMBERSTRADEDEALLOG):
    case FAILURE(ACTION_TYPES.CREATE_MEMBERSTRADEDEALLOG):
    case FAILURE(ACTION_TYPES.UPDATE_MEMBERSTRADEDEALLOG):
    case FAILURE(ACTION_TYPES.PARTIAL_UPDATE_MEMBERSTRADEDEALLOG):
    case FAILURE(ACTION_TYPES.DELETE_MEMBERSTRADEDEALLOG):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_MEMBERSTRADEDEALLOG_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_MEMBERSTRADEDEALLOG):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_MEMBERSTRADEDEALLOG):
    case SUCCESS(ACTION_TYPES.UPDATE_MEMBERSTRADEDEALLOG):
    case SUCCESS(ACTION_TYPES.PARTIAL_UPDATE_MEMBERSTRADEDEALLOG):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_MEMBERSTRADEDEALLOG):
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

const apiUrl = 'api/members-trade-deal-logs';

// Actions

export const getEntities: ICrudGetAllAction<IMembersTradeDealLog> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_MEMBERSTRADEDEALLOG_LIST,
  payload: axios.get<IMembersTradeDealLog>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<IMembersTradeDealLog> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_MEMBERSTRADEDEALLOG,
    payload: axios.get<IMembersTradeDealLog>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IMembersTradeDealLog> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_MEMBERSTRADEDEALLOG,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IMembersTradeDealLog> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_MEMBERSTRADEDEALLOG,
    payload: axios.put(`${apiUrl}/${entity.id}`, cleanEntity(entity)),
  });
  return result;
};

export const partialUpdate: ICrudPutAction<IMembersTradeDealLog> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.PARTIAL_UPDATE_MEMBERSTRADEDEALLOG,
    payload: axios.patch(`${apiUrl}/${entity.id}`, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IMembersTradeDealLog> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_MEMBERSTRADEDEALLOG,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
