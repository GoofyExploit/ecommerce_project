import React, { useState, useEffect } from 'react';
import { Order } from '../types';
import { orderService } from '../services/api';
import { useAuth } from '../hooks/useAuth';

const Orders: React.FC = () => {
  const [orders, setOrders] = useState<Order[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);
  const { user } = useAuth();

  useEffect(() => {
    const fetchOrders = async () => {
      if (!user) return;

      try {
        setLoading(true);
        const data = await orderService.getOrdersByCustomer(user.id);
        setOrders(data);
      } catch (err) {
        setError('Failed to load orders. Please try again later.');
        console.error('Error fetching orders:', err);
      } finally {
        setLoading(false);
      }
    };

    fetchOrders();
  }, [user]);

  if (!user) {
    return (
      <div className="text-center">
        <h1>My Orders</h1>
        <p>Please login to view your orders.</p>
      </div>
    );
  }

  if (loading) {
    return <div className="loading">Loading your orders...</div>;
  }

  if (error) {
    return <div className="error">{error}</div>;
  }

  return (
    <div>
      <h1>My Orders</h1>
      
      {orders.length === 0 ? (
        <div className="text-center">
          <p className="mb-3">You haven't placed any orders yet.</p>
          <a href="/products" className="btn btn-primary">
            Start Shopping
          </a>
        </div>
      ) : (
        <div>
          {orders.map((order) => (
            <div key={order.id} className="card mb-3">
              <div className="card-header">
                <div className="flex-between">
                  <h3>Order #{order.id}</h3>
                  <span>Customer ID: {order.customerId}</span>
                </div>
              </div>
              <div className="card-body">
                <h4>Items:</h4>
                {order.items.length === 0 ? (
                  <p>No items in this order.</p>
                ) : (
                  <ul>
                    {order.items.map((item, index) => (
                      <li key={index}>
                        Product ID: {item.productId} - Quantity: {item.quantity}
                      </li>
                    ))}
                  </ul>
                )}
              </div>
            </div>
          ))}
        </div>
      )}
    </div>
  );
};

export default Orders;
