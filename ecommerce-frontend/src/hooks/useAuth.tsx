import React, { createContext, useContext, useState, useEffect, ReactNode } from 'react';
import { User } from '../types';
import { userService } from '../services/api';

interface AuthContextType {
  user: User | null;
  login: (username: string, password: string) => Promise<boolean>;
  register: (username: string, password: string) => Promise<boolean>;
  logout: () => void;
  loading: boolean;
}

const AuthContext = createContext<AuthContextType | undefined>(undefined);

export const useAuth = () => {
  const context = useContext(AuthContext);
  if (context === undefined) {
    throw new Error('useAuth must be used within an AuthProvider');
  }
  return context;
};

interface AuthProviderProps {
  children: ReactNode;
}

export const AuthProvider: React.FC<AuthProviderProps> = ({ children }) => {
  const [user, setUser] = useState<User | null>(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const token = localStorage.getItem('authToken');
    if (token) {
      // In a real app, you'd validate the token with the server
      // For now, we'll just check if it exists
      setUser({ id: '1', username: 'user', role: 'USER' });
    }
    setLoading(false);
  }, []);

  const login = async (username: string, password: string): Promise<boolean> => {
    try {
      const userData = await userService.login({ username, password });
      setUser(userData);
      localStorage.setItem('authToken', 'dummy-token');
      return true;
    } catch (error: any) {
      console.error('Login failed:', error.message);
      // You could also show a toast notification here
      return false;
    }
  };

  const register = async (username: string, password: string): Promise<boolean> => {
    try {
      const userData = await userService.register({
        username,
        password,
        role: 'USER'
      });
      setUser(userData);
      localStorage.setItem('authToken', 'dummy-token');
      return true;
    } catch (error) {
      console.error('Registration failed:', error);
      return false;
    }
  };

  const logout = () => {
    setUser(null);
    localStorage.removeItem('authToken');
  };

  const value = {
    user,
    login,
    register,
    logout,
    loading
  };

  return (
    <AuthContext.Provider value={value}>
      {children}
    </AuthContext.Provider>
  );
};
