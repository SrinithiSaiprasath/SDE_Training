import apiClient from '../api/apiClient';

const authService = {
  signup: (userData) => apiClient.post('/auth/signup', userData),
  login: (credentials) => apiClient.post('/auth/login', credentials).then((response) => {
    localStorage.setItem('token', response.data.token); // Save the token in local storage
  }),
  logout: () => {
    localStorage.removeItem('token');
  },
};

export default authService;
