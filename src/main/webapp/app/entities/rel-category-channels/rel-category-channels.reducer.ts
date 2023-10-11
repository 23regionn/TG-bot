import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IRelCategoryChannels, defaultValue } from 'app/shared/model/rel-category-channels.model';
import { getChannelsByCategoryId } from 'app/entities/chanell/chanell.reducer';

export const ACTION_TYPES = {
  FETCH_RELCATEGORYCHANNELS_LIST: 'relCategoryChannels/FETCH_RELCATEGORYCHANNELS_LIST',
  FETCH_RELCATEGORYCHANNELS: 'relCategoryChannels/FETCH_RELCATEGORYCHANNELS',
  CREATE_RELCATEGORYCHANNELS: 'relCategoryChannels/CREATE_RELCATEGORYCHANNELS',
  UPDATE_RELCATEGORYCHANNELS: 'relCategoryChannels/UPDATE_RELCATEGORYCHANNELS',
  PARTIAL_UPDATE_RELCATEGORYCHANNELS: 'relCategoryChannels/PARTIAL_UPDATE_RELCATEGORYCHANNELS',
  DELETE_RELCATEGORYCHANNELS: 'relCategoryChannels/DELETE_RELCATEGORYCHANNELS',
  RESET: 'relCategoryChannels/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IRelCategoryChannels>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type RelCategoryChannelsState = Readonly<typeof initialState>;

// Reducer

export default (state: RelCategoryChannelsState = initialState, action): RelCategoryChannelsState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_RELCATEGORYCHANNELS_LIST):
    case REQUEST(ACTION_TYPES.FETCH_RELCATEGORYCHANNELS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_RELCATEGORYCHANNELS):
    case REQUEST(ACTION_TYPES.UPDATE_RELCATEGORYCHANNELS):
    case REQUEST(ACTION_TYPES.DELETE_RELCATEGORYCHANNELS):
    case REQUEST(ACTION_TYPES.PARTIAL_UPDATE_RELCATEGORYCHANNELS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_RELCATEGORYCHANNELS_LIST):
    case FAILURE(ACTION_TYPES.FETCH_RELCATEGORYCHANNELS):
    case FAILURE(ACTION_TYPES.CREATE_RELCATEGORYCHANNELS):
    case FAILURE(ACTION_TYPES.UPDATE_RELCATEGORYCHANNELS):
    case FAILURE(ACTION_TYPES.PARTIAL_UPDATE_RELCATEGORYCHANNELS):
    case FAILURE(ACTION_TYPES.DELETE_RELCATEGORYCHANNELS):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_RELCATEGORYCHANNELS_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_RELCATEGORYCHANNELS):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_RELCATEGORYCHANNELS):
    case SUCCESS(ACTION_TYPES.UPDATE_RELCATEGORYCHANNELS):
    case SUCCESS(ACTION_TYPES.PARTIAL_UPDATE_RELCATEGORYCHANNELS):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_RELCATEGORYCHANNELS):
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

const apiUrl = 'api/rel-category-channels';

// Actions

export const getEntities: ICrudGetAllAction<IRelCategoryChannels> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_RELCATEGORYCHANNELS_LIST,
  payload: axios.get<IRelCategoryChannels>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<IRelCategoryChannels> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_RELCATEGORYCHANNELS,
    payload: axios.get<IRelCategoryChannels>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IRelCategoryChannels> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_RELCATEGORYCHANNELS,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IRelCategoryChannels> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_RELCATEGORYCHANNELS,
    payload: axios.put(`${apiUrl}/${entity.id}`, cleanEntity(entity)),
  });
  return result;
};

export const partialUpdate: ICrudPutAction<IRelCategoryChannels> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.PARTIAL_UPDATE_RELCATEGORYCHANNELS,
    payload: axios.patch(`${apiUrl}/${entity.id}`, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IRelCategoryChannels> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_RELCATEGORYCHANNELS,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});

export const getEntitiesByCategoryId: any = id => ({
  type: ACTION_TYPES.FETCH_RELCATEGORYCHANNELS_LIST,
  payload: axios.get<IRelCategoryChannels>(`${apiUrl}/by-category/${id}`),
});

export const partialUpdateRel: any = rel => async dispatch => {
  const entity = {
    id: rel?.id,
    scoreChannel: rel?.scoreChannel,
    isShowChannel: rel?.isShowChannel,
  };

  const result = await dispatch({
    type: ACTION_TYPES.PARTIAL_UPDATE_RELCATEGORYCHANNELS,
    payload: axios.patch(`${apiUrl}/${entity.id}`, cleanEntity(entity)),
  });

  dispatch(getEntitiesByCategoryId(rel?.idCat));
  return result;
};

export const createRelCategoryChannel: any = rel => async dispatch => {
  const entity = {
    scoreChannel: rel?.scoreChannel,
    isShowChannel: rel?.isShowChannel,
    idCat: rel?.idCat,
    idChannel: rel?.idChannel,
  };

  const result = await dispatch({
    type: ACTION_TYPES.CREATE_RELCATEGORYCHANNELS,
    payload: axios.post(`${apiUrl}/by-ids`, cleanEntity(entity)),
  });
  dispatch(getEntitiesByCategoryId(rel?.idCat));
  return result;
};
