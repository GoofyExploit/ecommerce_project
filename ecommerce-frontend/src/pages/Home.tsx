import React from 'react';
import { Link } from 'react-router-dom';

const Home: React.FC = () => {
  return (
    <div>
      <div className="text-center mb-3">
        <h1>Welcome to Our E-Commerce Store</h1>
        <p className="mb-3">
          Discover amazing products at great prices. Shop with confidence and enjoy fast delivery!
        </p>
        <div className="flex-center gap-2">
          <Link to="/products" className="btn btn-primary">
            Browse Products
          </Link>
          <Link to="/cart" className="btn btn-outline">
            View Cart
          </Link>
        </div>
      </div>

      <div className="product-grid">
        <div className="card">
          <div className="card-header">
            <h3>🚀 Fast Delivery</h3>
          </div>
          <div className="card-body">
            <p>Get your orders delivered within 24-48 hours with our express shipping service.</p>
          </div>
        </div>

        <div className="card">
          <div className="card-header">
            <h3>💳 Secure Payment</h3>
          </div>
          <div className="card-body">
            <p>Your payment information is encrypted and secure with our advanced security measures.</p>
          </div>
        </div>

        <div className="card">
          <div className="card-header">
            <h3>🔄 Easy Returns</h3>
          </div>
          <div className="card-body">
            <p>Not satisfied? Return any item within 30 days for a full refund, no questions asked.</p>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Home;
