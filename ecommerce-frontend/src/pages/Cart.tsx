import React, { useState } from 'react';
import { useCart } from '../hooks/useCart';
import { useAuth } from '../hooks/useAuth';
import { orderService } from '../services/api';
import { useNavigate } from 'react-router-dom';

const Cart: React.FC = () => {
  const { items, updateQuantity, removeItem, clearCart, getTotalPrice } = useCart();
  const { user } = useAuth();
  const navigate = useNavigate();
  const [isCheckingOut, setIsCheckingOut] = useState(false);

  const handleCheckout = async () => {
    if (!user) {
      navigate('/login');
      return;
    }

    if (items.length === 0) {
      alert('Your cart is empty!');
      return;
    }

    try {
      setIsCheckingOut(true);
      
      const order = {
        customerId: user.id,
        items: items.map(item => ({
          id: item.product.id,
          productId: item.product.id,
          quantity: item.quantity
        }))
      };

      await orderService.createOrder(order);
      clearCart();
      alert('Order placed successfully!');
      navigate('/orders');
    } catch (error) {
      console.error('Checkout failed:', error);
      alert('Failed to place order. Please try again.');
    } finally {
      setIsCheckingOut(false);
    }
  };

  if (items.length === 0) {
    return (
      <div className="text-center">
        <h1>Your Cart</h1>
        <p className="mb-3">Your cart is empty</p>
        <a href="/products" className="btn btn-primary">
          Continue Shopping
        </a>
      </div>
    );
  }

  return (
    <div>
      <h1>Your Cart</h1>
      
      <div className="card">
        {items.map((item) => (
          <div key={item.product.id} className="cart-item">
            <div className="cart-item-info">
              <div className="cart-item-name">{item.product.name}</div>
              <div className="cart-item-price">${item.product.price.toFixed(2)}</div>
            </div>
            
            <div className="cart-item-controls">
              <button
                onClick={() => updateQuantity(item.product.id, item.quantity - 1)}
                className="quantity-btn"
              >
                -
              </button>
              <input
                type="number"
                value={item.quantity}
                onChange={(e) => updateQuantity(item.product.id, parseInt(e.target.value) || 0)}
                className="quantity-input"
                min="1"
              />
              <button
                onClick={() => updateQuantity(item.product.id, item.quantity + 1)}
                className="quantity-btn"
              >
                +
              </button>
              <button
                onClick={() => removeItem(item.product.id)}
                className="btn btn-danger"
                style={{ marginLeft: '1rem' }}
              >
                Remove
              </button>
            </div>
          </div>
        ))}
        
        <div className="cart-total">
          <div className="cart-total-row">
            <span>Subtotal:</span>
            <span>${getTotalPrice().toFixed(2)}</span>
          </div>
          <div className="cart-total-row">
            <span>Shipping:</span>
            <span>Free</span>
          </div>
          <div className="cart-total-row">
            <span>Total:</span>
            <span>${getTotalPrice().toFixed(2)}</span>
          </div>
        </div>
        
        <div className="flex-between mt-3">
          <button
            onClick={clearCart}
            className="btn btn-secondary"
          >
            Clear Cart
          </button>
          <button
            onClick={handleCheckout}
            className="btn btn-success"
            disabled={isCheckingOut}
          >
            {isCheckingOut ? 'Processing...' : 'Checkout'}
          </button>
        </div>
      </div>
    </div>
  );
};

export default Cart;
