import React from 'react';
import { Product } from '../types';
import { useCart } from '../hooks/useCart';

interface ProductCardProps {
  product: Product;
}

const ProductCard: React.FC<ProductCardProps> = ({ product }) => {
  const { addItem } = useCart();

  const handleAddToCart = () => {
    addItem(product, 1);
  };

  return (
    <div className="card product-card">
      <div className="product-image">
        🛍️
      </div>
      <div className="product-info">
        <h3 className="product-name">{product.name}</h3>
        <p className="product-price">${product.price.toFixed(2)}</p>
        <button 
          onClick={handleAddToCart}
          className="btn btn-primary"
        >
          Add to Cart
        </button>
      </div>
    </div>
  );
};

export default ProductCard;
