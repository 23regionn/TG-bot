import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IRelCategoryCityChannels, defaultValue } from 'app/shared/model/rel-category-city-channels.model';
import { IChanell } from 'app/shared/model/chanell.model';
import { getEntitiesByCategoryId } from 'app/entities/rel-category-channels/rel-category-channels.reducer';

export const ACTION_TYPES = {
  FETCH_RELCATEGORYCITYCHANNELS_LIST: 'relCategoryCityChannels/FETCH_RELCATEGORYCITYCHANNELS_LIST',
  FETCH_RELCATEGORYCITYCHANNELS: 'relCategoryCityChannels/FETCH_RELCATEGORYCITYCHANNELS',
  CREATE_RELCATEGORYCITYCHANNELS: 'relCategoryCityChannels/CREATE_RELCATEGORYCITYCHANNELS',
  UPDATE_RELCATEGORYCITYCHANNELS: 'relCategoryCityChannels/UPDATE_RELCATEGORYCITYCHANNELS',
  PARTIAL_UPDATE_RELCATEGORYCITYCHANNELS: 'relCategoryCityChannels/PARTIAL_UPDATE_RELCATEGORYCITYCHANNELS',
  DELETE_RELCATEGORYCITYCHANNELS: 'relCategoryCityChannels/DELETE_RELCATEGORYCITYCHANNELS',
  RESET: 'relCategoryCityChannels/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IRelCategoryCityChannels>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type RelCategoryCityChannelsState = Readonly<typeof initialState>;

// Reducer

export default (state: RelCategoryCityChannelsState = initialState, action): RelCategoryCityChannelsState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_RELCATEGORYCITYCHANNELS_LIST):
    case REQUEST(ACTION_TYPES.FETCH_RELCATEGORYCITYCHANNELS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_RELCATEGORYCITYCHANNELS):
    case REQUEST(ACTION_TYPES.UPDATE_RELCATEGORYCITYCHANNELS):
    case REQUEST(ACTION_TYPES.DELETE_RELCATEGORYCITYCHANNELS):
    case REQUEST(ACTION_TYPES.PARTIAL_UPDATE_RELCATEGORYCITYCHANNELS):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_RELCATEGORYCITYCHANNELS_LIST):
    case FAILURE(ACTION_TYPES.FETCH_RELCATEGORYCITYCHANNELS):
    case FAILURE(ACTION_TYPES.CREATE_RELCATEGORYCITYCHANNELS):
    case FAILURE(ACTION_TYPES.UPDATE_RELCATEGORYCITYCHANNELS):
    case FAILURE(ACTION_TYPES.PARTIAL_UPDATE_RELCATEGORYCITYCHANNELS):
    case FAILURE(ACTION_TYPES.DELETE_RELCATEGORYCITYCHANNELS):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_RELCATEGORYCITYCHANNELS_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_RELCATEGORYCITYCHANNELS):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_RELCATEGORYCITYCHANNELS):
    case SUCCESS(ACTION_TYPES.UPDATE_RELCATEGORYCITYCHANNELS):
    case SUCCESS(ACTION_TYPES.PARTIAL_UPDATE_RELCATEGORYCITYCHANNELS):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_RELCATEGORYCITYCHANNELS):
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

const apiUrl = 'api/rel-category-city-channels';

// Actions

export const getEntities: ICrudGetAllAction<IRelCategoryCityChannels> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_RELCATEGORYCITYCHANNELS_LIST,
  payload: axios.get<IRelCategoryCityChannels>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<IRelCategoryCityChannels> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_RELCATEGORYCITYCHANNELS,
    payload: axios.get<IRelCategoryCityChannels>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IRelCategoryCityChannels> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_RELCATEGORYCITYCHANNELS,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IRelCategoryCityChannels> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_RELCATEGORYCITYCHANNELS,
    payload: axios.put(`${apiUrl}/${entity.id}`, cleanEntity(entity)),
  });
  return result;
};

export const partialUpdate: ICrudPutAction<IRelCategoryCityChannels> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.PARTIAL_UPDATE_RELCATEGORYCITYCHANNELS,
    payload: axios.patch(`${apiUrl}/${entity.id}`, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IRelCategoryCityChannels> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_RELCATEGORYCITYCHANNELS,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});

export const getCategoryCityChannelsByRelCityCategory: any = (relCategoryCityId: number) => ({
  type: ACTION_TYPES.FETCH_RELCATEGORYCITYCHANNELS_LIST,
  payload: axios.get<IRelCategoryCityChannels>(`${apiUrl}/by-rel-city-and-category-id/${relCategoryCityId}`),
});

export const partialUpdateRel: any = rel => async dispatch => {
  const entity = {
    id: rel?.id,
    scoreChannel: rel?.scoreChannel,
    isShowChannel: rel?.isShowChannel,
    comment: rel?.comment,
  };

  const result = await dispatch({
    type: ACTION_TYPES.PARTIAL_UPDATE_RELCATEGORYCITYCHANNELS,
    payload: axios.patch(`${apiUrl}/${entity.id}`, cleanEntity(entity)),
  });

  dispatch(getCategoryCityChannelsByRelCityCategory(rel?.idRel));
  return result;
};

export const createRelCategoryCityChannel: any = rel => async dispatch => {
  const entity = {
    scoreChannel: rel?.scoreChannel,
    isShowChannel: rel?.isShowChannel,
    idRel: rel?.idRel,
    idChannel: rel?.idChannel,
    comment: rel?.comment,
  };

  const result = await dispatch({
    type: ACTION_TYPES.CREATE_RELCATEGORYCITYCHANNELS,
    payload: axios.post(`${apiUrl}/by-ids`, cleanEntity(entity)),
  });
  dispatch(getCategoryCityChannelsByRelCityCategory(rel?.idRel));
  return result;
};
