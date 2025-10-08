# E-Commerce Frontend

A modern React frontend for the e-commerce application built with TypeScript, Vite, and React Router.

## Features

- 🛍️ **Product Catalog**: Browse and search products
- 🛒 **Shopping Cart**: Add/remove items, update quantities
- 👤 **User Authentication**: Login and registration
- 📦 **Order Management**: View order history
- 📱 **Responsive Design**: Works on all devices
- 🎨 **Modern UI**: Clean and intuitive interface

## Tech Stack

- **React 18** with TypeScript
- **Vite** for fast development and building
- **React Router** for navigation
- **Axios** for API calls
- **CSS3** with modern styling

## Getting Started

### Prerequisites

- Node.js (v16 or higher)
- npm or yarn

### Installation

1. Install dependencies:
```bash
npm install
```

2. Start the development server:
```bash
npm run dev
```

3. Open your browser and navigate to `http://localhost:3000`

### Building for Production

```bash
npm run build
```

The built files will be in the `dist` directory.

## API Integration

The frontend is configured to work with the following backend services:

- **Product Service** (port 8081): `/api/products`
- **User Service** (port 8082): `/api/users`
- **Order Service** (port 8083): `/api/orders`

The Vite development server is configured with proxy settings to route API calls to the appropriate backend services.

## Project Structure

```
src/
├── components/          # Reusable UI components
│   ├── Header.tsx      # Navigation header
│   └── ProductCard.tsx # Product display card
├── pages/              # Page components
│   ├── Home.tsx        # Landing page
│   ├── Products.tsx    # Product listing
│   ├── Cart.tsx        # Shopping cart
│   ├── Login.tsx       # User login
│   ├── Register.tsx    # User registration
│   └── Orders.tsx      # Order history
├── hooks/              # Custom React hooks
│   ├── useAuth.tsx     # Authentication logic
│   └── useCart.tsx     # Shopping cart logic
├── services/           # API services
│   └── api.ts          # API client and services
├── types/              # TypeScript type definitions
│   └── index.ts        # Shared types
├── App.tsx             # Main app component
├── App.css             # Global styles
└── main.tsx            # Application entry point
```

## Features Overview

### Authentication
- User registration and login
- Protected routes
- Session management

### Shopping Cart
- Add/remove products
- Update quantities
- Persistent cart (localStorage)
- Real-time total calculation

### Product Management
- Product listing
- Product details
- Search and filtering (ready for implementation)

### Order Management
- Place orders
- View order history
- Order status tracking

## Development

### Adding New Features

1. Create components in `src/components/`
2. Add pages in `src/pages/`
3. Define types in `src/types/`
4. Add API services in `src/services/`
5. Create custom hooks in `src/hooks/`

### Styling

The application uses CSS modules with a modern design system:
- Consistent color palette
- Responsive grid system
- Modern button and form styles
- Mobile-first approach

## Backend Integration

Make sure the following backend services are running:

1. **Product Service** on port 8081
2. **User Service** on port 8082  
3. **Order Service** on port 8083

The frontend will automatically proxy API calls to these services during development.

## Contributing

1. Follow the existing code structure
2. Use TypeScript for type safety
3. Write clean, readable CSS
4. Test your changes thoroughly
5. Follow React best practices
