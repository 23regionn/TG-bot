import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IConfigTable, defaultValue } from 'app/shared/model/config-table.model';

export const ACTION_TYPES = {
  FETCH_CONFIGTABLE_LIST: 'configTable/FETCH_CONFIGTABLE_LIST',
  FETCH_CONFIGTABLE: 'configTable/FETCH_CONFIGTABLE',
  CREATE_CONFIGTABLE: 'configTable/CREATE_CONFIGTABLE',
  UPDATE_CONFIGTABLE: 'configTable/UPDATE_CONFIGTABLE',
  PARTIAL_UPDATE_CONFIGTABLE: 'configTable/PARTIAL_UPDATE_CONFIGTABLE',
  DELETE_CONFIGTABLE: 'configTable/DELETE_CONFIGTABLE',
  RESET: 'configTable/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IConfigTable>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type ConfigTableState = Readonly<typeof initialState>;

// Reducer

export default (state: ConfigTableState = initialState, action): ConfigTableState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_CONFIGTABLE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_CONFIGTABLE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_CONFIGTABLE):
    case REQUEST(ACTION_TYPES.UPDATE_CONFIGTABLE):
    case REQUEST(ACTION_TYPES.DELETE_CONFIGTABLE):
    case REQUEST(ACTION_TYPES.PARTIAL_UPDATE_CONFIGTABLE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_CONFIGTABLE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_CONFIGTABLE):
    case FAILURE(ACTION_TYPES.CREATE_CONFIGTABLE):
    case FAILURE(ACTION_TYPES.UPDATE_CONFIGTABLE):
    case FAILURE(ACTION_TYPES.PARTIAL_UPDATE_CONFIGTABLE):
    case FAILURE(ACTION_TYPES.DELETE_CONFIGTABLE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_CONFIGTABLE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_CONFIGTABLE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_CONFIGTABLE):
    case SUCCESS(ACTION_TYPES.UPDATE_CONFIGTABLE):
    case SUCCESS(ACTION_TYPES.PARTIAL_UPDATE_CONFIGTABLE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_CONFIGTABLE):
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

const apiUrl = 'api/config-tables';

// Actions

export const getEntities: ICrudGetAllAction<IConfigTable> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_CONFIGTABLE_LIST,
  payload: axios.get<IConfigTable>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<IConfigTable> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_CONFIGTABLE,
    payload: axios.get<IConfigTable>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IConfigTable> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_CONFIGTABLE,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IConfigTable> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_CONFIGTABLE,
    payload: axios.put(`${apiUrl}/${entity.id}`, cleanEntity(entity)),
  });
  return result;
};

export const partialUpdate: ICrudPutAction<IConfigTable> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.PARTIAL_UPDATE_CONFIGTABLE,
    payload: axios.patch(`${apiUrl}/${entity.id}`, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IConfigTable> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_CONFIGTABLE,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
