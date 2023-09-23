import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ICategoryList, defaultValue } from 'app/shared/model/category-list.model';
// import {ICategoryList} from "app/shared/model/category-list.model";
import { ICategory } from 'app/shared/model/category.model';

export const ACTION_TYPES = {
  FETCH_ALL_CATEGORIES_LIST: 'all-categories/FETCH_ALL_CATEGORIES_LIST',
  FETCH_ALL_CATEGORIES: 'all-categories/FETCH_ALL_CATEGORIES',
  CREATE_ALL_CATEGORIES: 'all-categories/CREATE_ALL_CATEGORIES',
  UPDATE_ALL_CATEGORIES: 'all-categories/UPDATE_ALL_CATEGORIES',
  PARTIAL_UPDATE_ALL_CATEGORIES: 'all-categories/PARTIAL_UPDATE_ALL_CATEGORIES',
  DELETE_ALL_CATEGORIES: 'all-categories/DELETE_ALL_CATEGORIES',
  RESET: 'all-categories/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ICategoryList>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type AllCategoriesState = Readonly<typeof initialState>;

// Reducer

export default (state: AllCategoriesState = initialState, action): AllCategoriesState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_ALL_CATEGORIES_LIST):
    case REQUEST(ACTION_TYPES.FETCH_ALL_CATEGORIES):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_ALL_CATEGORIES):
    case REQUEST(ACTION_TYPES.UPDATE_ALL_CATEGORIES):
    case REQUEST(ACTION_TYPES.DELETE_ALL_CATEGORIES):
    case REQUEST(ACTION_TYPES.PARTIAL_UPDATE_ALL_CATEGORIES):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_ALL_CATEGORIES_LIST):
    case FAILURE(ACTION_TYPES.FETCH_ALL_CATEGORIES):
    case FAILURE(ACTION_TYPES.CREATE_ALL_CATEGORIES):
    case FAILURE(ACTION_TYPES.UPDATE_ALL_CATEGORIES):
    case FAILURE(ACTION_TYPES.PARTIAL_UPDATE_ALL_CATEGORIES):
    case FAILURE(ACTION_TYPES.DELETE_ALL_CATEGORIES):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_ALL_CATEGORIES_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_ALL_CATEGORIES):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_ALL_CATEGORIES):
    case SUCCESS(ACTION_TYPES.UPDATE_ALL_CATEGORIES):
    case SUCCESS(ACTION_TYPES.PARTIAL_UPDATE_ALL_CATEGORIES):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_ALL_CATEGORIES):
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

const apiUrl = 'api/categories';
const apiOnlyCategories = 'api/only-categories';
const apiUrlCategories = 'api/categories';
const apiUrlAddForCity = 'api/categories-add-for-city';

// Actions

export const getEntities: ICrudGetAllAction<ICategoryList> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_ALL_CATEGORIES_LIST,
  payload: axios.get<ICategoryList>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<ICategoryList> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_ALL_CATEGORIES,
    payload: axios.get<ICategoryList>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<ICategoryList> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_ALL_CATEGORIES,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ICategoryList> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_ALL_CATEGORIES,
    payload: axios.put(`${apiUrl}/${entity.id}`, cleanEntity(entity)),
  });
  return result;
};

export const partialUpdate: ICrudPutAction<ICategoryList> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.PARTIAL_UPDATE_ALL_CATEGORIES,
    payload: axios.patch(`${apiUrl}/${entity.id}`, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<ICategoryList> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_ALL_CATEGORIES,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});

// МОИ ЗАПРОСЫ
export const getOnlyCategories: ICrudGetAllAction<ICategoryList> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_ALL_CATEGORIES_LIST,
  payload: axios.get<ICategoryList>(`${apiOnlyCategories}?cacheBuster=${new Date().getTime()}`),
});

export const getEntityCategory: any = id => {
  const requestUrl = `${apiUrlCategories}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_ALL_CATEGORIES,
    payload: axios.get<ICategoryList>(requestUrl),
  };
};

export const partialUpdateCategory: any = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.PARTIAL_UPDATE_ALL_CATEGORIES,
    payload: axios.patch(`${apiUrl}/${entity.id}`, cleanEntity(entity)),
  });
  dispatch(getOnlyCategories());
  return result;
};

export const createCategory: any = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_ALL_CATEGORIES,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getOnlyCategories());
  return result;
};

export const getEntityCategoryById: any = id => {
  const requestUrl = `${apiUrlCategories}/by-id/${id}`;
  return {
    type: ACTION_TYPES.FETCH_ALL_CATEGORIES,
    payload: axios.get<ICategoryList>(requestUrl),
  };
};

export const getCategoriesByCityId: any = id => {
  const requestUrlByCityId = `${apiUrlCategories}/by-city-id/${id}`;
  return {
    type: ACTION_TYPES.FETCH_ALL_CATEGORIES_LIST,
    payload: axios.get<ICategoryList>(`${requestUrlByCityId}`),
  };
};

export const addCategoryToCity: any = entityCat => async dispatch => {
  const entity = {
    idCat: entityCat?.idCat,
    idCity: entityCat?.idCity,
  };

  const result = await dispatch({
    type: ACTION_TYPES.CREATE_ALL_CATEGORIES,
    payload: axios.post(apiUrlAddForCity, cleanEntity(entity)),
  });
  dispatch(getCategoriesByCityId(entityCat?.idCity));
  return result;
};

export const getInfoAboutCategoryAndCity: any = (cityId: number, categoryId: number) => {
  const requestUrl = `${apiUrlCategories}/info-with-city/${cityId}/${categoryId}`;
  return {
    type: ACTION_TYPES.FETCH_ALL_CATEGORIES,
    payload: axios.get<any>(requestUrl),
  };
};
