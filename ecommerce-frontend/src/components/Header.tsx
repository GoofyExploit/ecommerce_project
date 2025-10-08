import React from 'react';
import { Link } from 'react-router-dom';
import { useAuth } from '../hooks/useAuth';
import { useCart } from '../hooks/useCart';

const Header: React.FC = () => {
  const { user, logout } = useAuth();
  const { getTotalItems } = useCart();

  return (
    <header className="header">
      <div className="header-content">
        <Link to="/" className="logo">
          🛒 E-Commerce Store
        </Link>
        
        <nav className="nav">
          <Link to="/">Home</Link>
          <Link to="/products">Products</Link>
          <Link to="/cart" className="cart-icon">
            🛒 Cart ({getTotalItems()})
          </Link>
          
          {user ? (
            <>
              <Link to="/orders">My Orders</Link>
              <span>Welcome, {user.username}!</span>
              <button onClick={logout} className="btn btn-outline">
                Logout
              </button>
            </>
          ) : (
            <>
              <Link to="/login">Login</Link>
              <Link to="/register">Register</Link>
            </>
          )}
        </nav>
      </div>
    </header>
  );
};

export default Header;
