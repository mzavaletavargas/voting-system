import axios from 'axios';

// Create an axios instance
const http = axios.create({
    baseURL: 'http://localhost:8080',
});

// Add a request interceptor
http.interceptors.request.use(
    config => {
        // Get the token from localStorage
        const token = localStorage.getItem('authToken');
        if (token) {
            // Attach the token to the Authorization header
            config.headers.Authorization = `Bearer ${token}`;
        }
        return config;
    },
    error => {
        return Promise.reject(error);
    }
);

export default http;
