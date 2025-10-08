import axios from 'axios';
import { Product, User, Order, LoginCredentials } from '../types';

const API_BASE_URL = '/api';

const api = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    'Content-Type': 'application/json',
  },
});

// Add auth token to requests if available
api.interceptors.request.use((config) => {
  const token = localStorage.getItem('authToken');
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
});

export const productService = {
  getAllProducts: async (): Promise<Product[]> => {
    const response = await api.get('/products');
    return response.data;
  },

  getProductById: async (id: string): Promise<Product> => {
    const response = await api.get(`/products/${id}`);
    return response.data;
  },

  createProduct: async (product: Omit<Product, 'id'>): Promise<Product> => {
    const response = await api.post('/products', product);
    return response.data;
  },

  updateProduct: async (id: string, product: Partial<Product>): Promise<Product> => {
    const response = await api.put(`/products/${id}`, product);
    return response.data;
  },

  deleteProduct: async (id: string): Promise<void> => {
    await api.delete(`/products/${id}`);
  },
};

export const userService = {
  login: async (credentials: LoginCredentials): Promise<User> => {
    try {
      const response = await api.post('/users/auth/login', credentials);
      if (response.status === 200) {
        return response.data;
      } else {
        throw new Error('Login failed');
      }
    } catch (error: any) {
      if (error.response?.status === 401) {
        throw new Error('Invalid username or password');
      } else if (error.response?.status === 400) {
        throw new Error('Please provide both username and password');
      } else {
        throw new Error('Login failed. Please try again.');
      }
    }
  },

  register: async (user: Omit<User, 'id'>): Promise<User> => {
    try {
      const response = await api.post('/users', user);
      return response.data;
    } catch (error: any) {
      if (error.response?.status === 400) {
        throw new Error('Registration failed. Username might already exist.');
      } else {
        throw new Error('Registration failed. Please try again.');
      }
    }
  },

  getCurrentUser: async (): Promise<User> => {
    const response = await api.get('/users/me');
    return response.data;
  },

  updateUser: async (id: string, user: Partial<User>): Promise<User> => {
    const response = await api.put(`/users/${id}`, user);
    return response.data;
  },
};

export const orderService = {
  getAllOrders: async (): Promise<Order[]> => {
    const response = await api.get('/orders');
    return response.data;
  },

  getOrderById: async (id: string): Promise<Order> => {
    const response = await api.get(`/orders/${id}`);
    return response.data;
  },

  getOrdersByCustomer: async (customerId: string): Promise<Order[]> => {
    const response = await api.get(`/orders/by-customer/${customerId}`);
    return response.data;
  },

  createOrder: async (order: Omit<Order, 'id'>): Promise<Order> => {
    const response = await api.post('/orders', order);
    return response.data;
  },

  updateOrder: async (id: string, order: Partial<Order>): Promise<Order> => {
    const response = await api.put(`/orders/${id}`, order);
    return response.data;
  },

  deleteOrder: async (id: string): Promise<void> => {
    await api.delete(`/orders/${id}`);
  },
};

export default api;
