export interface Product {
  id: string;
  name: string;
  price: number;
}

export interface User {
  id: string;
  username: string;
  password?: string;
  role: string;
}

export interface OrderItem {
  id: string;
  productId: string;
  quantity: number;
}

export interface Order {
  id: string;
  customerId: string;
  items: OrderItem[];
}

export interface CartItem {
  product: Product;
  quantity: number;
}

export interface LoginCredentials {
  username: string;
  password: string;
}

export interface ApiResponse<T> {
  data: T;
  message?: string;
  error?: string;
}
