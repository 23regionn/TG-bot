import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IMembersTradeDeal, defaultValue } from 'app/shared/model/members-trade-deal.model';

export const ACTION_TYPES = {
  FETCH_MEMBERSTRADEDEAL_LIST: 'membersTradeDeal/FETCH_MEMBERSTRADEDEAL_LIST',
  FETCH_MEMBERSTRADEDEAL: 'membersTradeDeal/FETCH_MEMBERSTRADEDEAL',
  CREATE_MEMBERSTRADEDEAL: 'membersTradeDeal/CREATE_MEMBERSTRADEDEAL',
  UPDATE_MEMBERSTRADEDEAL: 'membersTradeDeal/UPDATE_MEMBERSTRADEDEAL',
  PARTIAL_UPDATE_MEMBERSTRADEDEAL: 'membersTradeDeal/PARTIAL_UPDATE_MEMBERSTRADEDEAL',
  DELETE_MEMBERSTRADEDEAL: 'membersTradeDeal/DELETE_MEMBERSTRADEDEAL',
  RESET: 'membersTradeDeal/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IMembersTradeDeal>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type MembersTradeDealState = Readonly<typeof initialState>;

// Reducer

export default (state: MembersTradeDealState = initialState, action): MembersTradeDealState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_MEMBERSTRADEDEAL_LIST):
    case REQUEST(ACTION_TYPES.FETCH_MEMBERSTRADEDEAL):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_MEMBERSTRADEDEAL):
    case REQUEST(ACTION_TYPES.UPDATE_MEMBERSTRADEDEAL):
    case REQUEST(ACTION_TYPES.DELETE_MEMBERSTRADEDEAL):
    case REQUEST(ACTION_TYPES.PARTIAL_UPDATE_MEMBERSTRADEDEAL):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_MEMBERSTRADEDEAL_LIST):
    case FAILURE(ACTION_TYPES.FETCH_MEMBERSTRADEDEAL):
    case FAILURE(ACTION_TYPES.CREATE_MEMBERSTRADEDEAL):
    case FAILURE(ACTION_TYPES.UPDATE_MEMBERSTRADEDEAL):
    case FAILURE(ACTION_TYPES.PARTIAL_UPDATE_MEMBERSTRADEDEAL):
    case FAILURE(ACTION_TYPES.DELETE_MEMBERSTRADEDEAL):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_MEMBERSTRADEDEAL_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_MEMBERSTRADEDEAL):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_MEMBERSTRADEDEAL):
    case SUCCESS(ACTION_TYPES.UPDATE_MEMBERSTRADEDEAL):
    case SUCCESS(ACTION_TYPES.PARTIAL_UPDATE_MEMBERSTRADEDEAL):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_MEMBERSTRADEDEAL):
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

const apiUrl = 'api/members-trade-deals';

// Actions

export const getEntities: ICrudGetAllAction<IMembersTradeDeal> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_MEMBERSTRADEDEAL_LIST,
  payload: axios.get<IMembersTradeDeal>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<IMembersTradeDeal> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_MEMBERSTRADEDEAL,
    payload: axios.get<IMembersTradeDeal>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IMembersTradeDeal> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_MEMBERSTRADEDEAL,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IMembersTradeDeal> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_MEMBERSTRADEDEAL,
    payload: axios.put(`${apiUrl}/${entity.id}`, cleanEntity(entity)),
  });
  return result;
};

export const partialUpdate: ICrudPutAction<IMembersTradeDeal> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.PARTIAL_UPDATE_MEMBERSTRADEDEAL,
    payload: axios.patch(`${apiUrl}/${entity.id}`, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IMembersTradeDeal> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_MEMBERSTRADEDEAL,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
