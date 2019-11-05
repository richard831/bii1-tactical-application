import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IBII, defaultValue } from 'app/shared/model/bii.model';

export const ACTION_TYPES = {
  FETCH_BII_LIST: 'bII/FETCH_BII_LIST',
  FETCH_BII: 'bII/FETCH_BII',
  CREATE_BII: 'bII/CREATE_BII',
  UPDATE_BII: 'bII/UPDATE_BII',
  DELETE_BII: 'bII/DELETE_BII',
  RESET: 'bII/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IBII>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type BIIState = Readonly<typeof initialState>;

// Reducer

export default (state: BIIState = initialState, action): BIIState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_BII_LIST):
    case REQUEST(ACTION_TYPES.FETCH_BII):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_BII):
    case REQUEST(ACTION_TYPES.UPDATE_BII):
    case REQUEST(ACTION_TYPES.DELETE_BII):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_BII_LIST):
    case FAILURE(ACTION_TYPES.FETCH_BII):
    case FAILURE(ACTION_TYPES.CREATE_BII):
    case FAILURE(ACTION_TYPES.UPDATE_BII):
    case FAILURE(ACTION_TYPES.DELETE_BII):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_BII_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_BII):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_BII):
    case SUCCESS(ACTION_TYPES.UPDATE_BII):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_BII):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {}
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState
      };
    default:
      return state;
  }
};

const apiUrl = 'api/biis';

// Actions

export const getEntities: ICrudGetAllAction<IBII> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_BII_LIST,
  payload: axios.get<IBII>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IBII> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_BII,
    payload: axios.get<IBII>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IBII> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_BII,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IBII> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_BII,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const deleteEntity: ICrudDeleteAction<IBII> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_BII,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
