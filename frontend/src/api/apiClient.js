import axios from 'axios';

// Create an Axios instance with default configurations
const apiClient = axios.create({
  baseURL: 'http://localhost:5000/api', // Backend base URL (change if your backend uses a different URL or port)
  headers: {
    'Content-Type': 'application/json',
  },
});

// Request interceptor to include the auth token in every request
apiClient.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem('token'); // Get the JWT token from local storage
    if (token) {
      config.headers.Authorization = `Bearer ${token}`; // Attach the token to the Authorization header
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

// Response interceptor for global error handling
apiClient.interceptors.response.use(
  (response) => response,
  (error) => {
    // Handle errors globally (e.g., log out if unauthorized)
    if (error.response?.status === 401) {
      localStorage.removeItem('token'); // Clear the token on unauthorized error
      window.location.href = '/login';  // Redirect to the login page
    }
    return Promise.reject(error);
  }
);

export default apiClient;
