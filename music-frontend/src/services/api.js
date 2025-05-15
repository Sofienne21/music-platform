import axios from 'axios';

const API_BASE = import.meta.env.VITE_API_URL;

export const createUser = (user) => axios.post(`${API_BASE}/users`, user);

export const addFavorite = (userId, track) =>
  axios.post(`${API_BASE}/favorites/${userId}`, track);

export const getFavorites = (userId) =>
  axios.get(`${API_BASE}/favorites/${userId}`);

export const deleteFavorite = (favoriteId) =>
  axios.delete(`${API_BASE}/favorites/${favoriteId}`);