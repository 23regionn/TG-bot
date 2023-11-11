import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ICountChannelClickPageLog, defaultValue } from 'app/shared/model/count-channel-click-page-log.model';

export const ACTION_TYPES = {
  FETCH_COUNTCHANNELCLICKPAGELOG_LIST: 'countChannelClickPageLog/FETCH_COUNTCHANNELCLICKPAGELOG_LIST',
  FETCH_COUNTCHANNELCLICKPAGELOG_LIST_BY_DATES: 'countChannelClickPageLog/FETCH_COUNTCHANNELCLICKPAGELOG_LIST_BY_DATES',
  FETCH_COUNTCHANNELCLICKPAGELOG: 'countChannelClickPageLog/FETCH_COUNTCHANNELCLICKPAGELOG',
  CREATE_COUNTCHANNELCLICKPAGELOG: 'countChannelClickPageLog/CREATE_COUNTCHANNELCLICKPAGELOG',
  UPDATE_COUNTCHANNELCLICKPAGELOG: 'countChannelClickPageLog/UPDATE_COUNTCHANNELCLICKPAGELOG',
  PARTIAL_UPDATE_COUNTCHANNELCLICKPAGELOG: 'countChannelClickPageLog/PARTIAL_UPDATE_COUNTCHANNELCLICKPAGELOG',
  DELETE_COUNTCHANNELCLICKPAGELOG: 'countChannelClickPageLog/DELETE_COUNTCHANNELCLICKPAGELOG',
  RESET: 'countChannelClickPageLog/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ICountChannelClickPageLog>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type CountChannelClickPageLogState = Readonly<typeof initialState>;

// Reducer

export default (state: CountChannelClickPageLogState = initialState, action): CountChannelClickPageLogState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_COUNTCHANNELCLICKPAGELOG_LIST):
    case REQUEST(ACTION_TYPES.FETCH_COUNTCHANNELCLICKPAGELOG):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_COUNTCHANNELCLICKPAGELOG):
    case REQUEST(ACTION_TYPES.UPDATE_COUNTCHANNELCLICKPAGELOG):
    case REQUEST(ACTION_TYPES.DELETE_COUNTCHANNELCLICKPAGELOG):
    case REQUEST(ACTION_TYPES.PARTIAL_UPDATE_COUNTCHANNELCLICKPAGELOG):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_COUNTCHANNELCLICKPAGELOG_LIST):
    case FAILURE(ACTION_TYPES.FETCH_COUNTCHANNELCLICKPAGELOG):
    case FAILURE(ACTION_TYPES.CREATE_COUNTCHANNELCLICKPAGELOG):
    case FAILURE(ACTION_TYPES.UPDATE_COUNTCHANNELCLICKPAGELOG):
    case FAILURE(ACTION_TYPES.PARTIAL_UPDATE_COUNTCHANNELCLICKPAGELOG):
    case FAILURE(ACTION_TYPES.DELETE_COUNTCHANNELCLICKPAGELOG):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_COUNTCHANNELCLICKPAGELOG_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_COUNTCHANNELCLICKPAGELOG):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_COUNTCHANNELCLICKPAGELOG):
    case SUCCESS(ACTION_TYPES.UPDATE_COUNTCHANNELCLICKPAGELOG):
    case SUCCESS(ACTION_TYPES.PARTIAL_UPDATE_COUNTCHANNELCLICKPAGELOG):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_COUNTCHANNELCLICKPAGELOG):
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

const apiUrl = 'api/count-channel-click-page-logs';

// Actions

export const getEntities: ICrudGetAllAction<ICountChannelClickPageLog> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_COUNTCHANNELCLICKPAGELOG_LIST,
  payload: axios.get<ICountChannelClickPageLog>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<ICountChannelClickPageLog> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_COUNTCHANNELCLICKPAGELOG,
    payload: axios.get<ICountChannelClickPageLog>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<ICountChannelClickPageLog> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_COUNTCHANNELCLICKPAGELOG,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ICountChannelClickPageLog> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_COUNTCHANNELCLICKPAGELOG,
    payload: axios.put(`${apiUrl}/${entity.id}`, cleanEntity(entity)),
  });
  return result;
};

export const partialUpdate: ICrudPutAction<ICountChannelClickPageLog> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.PARTIAL_UPDATE_COUNTCHANNELCLICKPAGELOG,
    payload: axios.patch(`${apiUrl}/${entity.id}`, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<ICountChannelClickPageLog> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_COUNTCHANNELCLICKPAGELOG,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});

export const searchTypeLogCountByPageNumberByDates: any = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.FETCH_COUNTCHANNELCLICKPAGELOG_LIST_BY_DATES,
    payload: axios.post(`${apiUrl}/detail/page-number/by-dates`, cleanEntity(entity)),
  });
  return result;
};

export const searchTypeLogCountByPageNumberByDatesForCity: any = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.FETCH_COUNTCHANNELCLICKPAGELOG_LIST_BY_DATES,
    payload: axios.post(`${apiUrl}/detail/for-city/page-number/by-dates`, cleanEntity(entity)),
  });
  return result;
};
