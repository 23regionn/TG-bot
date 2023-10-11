import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IChanell, defaultValue } from 'app/shared/model/chanell.model';
import { getOnlyCategories } from 'app/modules/all-categories/all-categories.reducer';

export const ACTION_TYPES = {
  FETCH_CHANELL_LIST: 'chanell/FETCH_CHANELL_LIST',
  FETCH_CHANELL: 'chanell/FETCH_CHANELL',
  CREATE_CHANELL: 'chanell/CREATE_CHANELL',
  UPDATE_CHANELL: 'chanell/UPDATE_CHANELL',
  PARTIAL_UPDATE_CHANELL: 'chanell/PARTIAL_UPDATE_CHANELL',
  DELETE_CHANELL: 'chanell/DELETE_CHANELL',
  RESET: 'chanell/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IChanell>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type ChanellState = Readonly<typeof initialState>;

// Reducer

export default (state: ChanellState = initialState, action): ChanellState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_CHANELL_LIST):
    case REQUEST(ACTION_TYPES.FETCH_CHANELL):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_CHANELL):
    case REQUEST(ACTION_TYPES.UPDATE_CHANELL):
    case REQUEST(ACTION_TYPES.DELETE_CHANELL):
    case REQUEST(ACTION_TYPES.PARTIAL_UPDATE_CHANELL):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_CHANELL_LIST):
    case FAILURE(ACTION_TYPES.FETCH_CHANELL):
    case FAILURE(ACTION_TYPES.CREATE_CHANELL):
    case FAILURE(ACTION_TYPES.UPDATE_CHANELL):
    case FAILURE(ACTION_TYPES.PARTIAL_UPDATE_CHANELL):
    case FAILURE(ACTION_TYPES.DELETE_CHANELL):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_CHANELL_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_CHANELL):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_CHANELL):
    case SUCCESS(ACTION_TYPES.UPDATE_CHANELL):
    case SUCCESS(ACTION_TYPES.PARTIAL_UPDATE_CHANELL):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_CHANELL):
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

const apiUrl = 'api/chanells';

// Actions

export const getEntities: ICrudGetAllAction<IChanell> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_CHANELL_LIST,
  payload: axios.get<IChanell>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<IChanell> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_CHANELL,
    payload: axios.get<IChanell>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IChanell> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_CHANELL,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IChanell> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_CHANELL,
    payload: axios.put(`${apiUrl}/${entity.id}`, cleanEntity(entity)),
  });
  return result;
};

export const partialUpdate: ICrudPutAction<IChanell> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.PARTIAL_UPDATE_CHANELL,
    payload: axios.patch(`${apiUrl}/${entity.id}`, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IChanell> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_CHANELL,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});

// Новые
export const getChannelsByCategoryId: any = id => ({
  type: ACTION_TYPES.FETCH_CHANELL_LIST,
  payload: axios.get<IChanell>(`${apiUrl}/by-category-id/${id}`),
});

export const partialUpdateChannelForCategoryPage: any = channel => async dispatch => {
  const entity = {
    id: channel?.id,
    name: channel?.name,
    score: channel?.score,
    link: channel?.link,
    priceForPay: channel?.priceForPay,
    comment: channel?.comment,
    contacts: channel?.contacts,
    isModerate: channel?.isModerate,
    isPay: channel?.isPay,
    startDate: channel?.startDate,
    lastPayDate: channel?.lastPayDate,
    endPublicDate: channel?.endPublicDate,
  };

  const result = await dispatch({
    type: ACTION_TYPES.PARTIAL_UPDATE_CHANELL,
    payload: axios.patch(`${apiUrl}/${entity.id}`, cleanEntity(entity)),
  });

  dispatch(getChannelsByCategoryId(channel?.idCat));
  return result;
};

export const createChannelForCategoryPage: any = channel => async dispatch => {
  const entity = {
    idCat: channel?.idCat,
    name: channel?.name,
    score: channel?.score,
    link: channel?.link,
    priceForPay: channel?.priceForPay,
    comment: channel?.comment,
    contacts: channel?.contacts,
    isModerate: channel?.isModerate,
    isPay: channel?.isPay,
    lastPayDate: channel?.lastPayDate,
    endPublicDate: channel?.endPublicDate,
  };

  const result = await dispatch({
    type: ACTION_TYPES.CREATE_CHANELL,
    payload: axios.post(`${apiUrl}/with-category-id`, cleanEntity(entity)),
  });
  dispatch(getChannelsByCategoryId(channel?.idCat));
  return result;
};

export const getChannelsByCityIdAndCategoryId: any = (cityId: number, categoryId: number) => ({
  type: ACTION_TYPES.FETCH_CHANELL_LIST,
  payload: axios.get<IChanell>(`${apiUrl}/by-city-id-and-category-id/${cityId}/${categoryId}`),
});

export const partialUpdateChannelForChannelsByCategoryAndCityPage: any = channel => async dispatch => {
  const entity = {
    id: channel?.id,
    name: channel?.name,
    score: channel?.score,
    link: channel?.link,
    priceForPay: channel?.priceForPay,
    comment: channel?.comment,
    contacts: channel?.contacts,
    isModerate: channel?.isModerate,
    isPay: channel?.isPay,
    startDate: channel?.startDate,
    lastPayDate: channel?.lastPayDate,
    endPublicDate: channel?.endPublicDate,
  };

  const result = await dispatch({
    type: ACTION_TYPES.PARTIAL_UPDATE_CHANELL,
    payload: axios.patch(`${apiUrl}/${entity.id}`, cleanEntity(entity)),
  });

  dispatch(getChannelsByCityIdAndCategoryId(channel?.idCity, channel?.idCat));
  return result;
};

export const getAllChanellsNamesAndIdDTO: any = () => ({
  type: ACTION_TYPES.FETCH_CHANELL_LIST,
  payload: axios.get<IChanell>(`${apiUrl}-only-id-and-name`),
});
