# E-Commerce Spring Boot Application

A complete e-commerce application with role-based access control and full CRUD operations.

## Features

### 🔐 Role-Based Access Control
- **Admin Role**: Full access to all CRUD operations
- **User Role**: Limited access to shopping and personal account management
- **Login System**: Secure authentication with username/password
- **Registration**: New user registration with automatic USER role assignment

### ✅ Complete CRUD Operations
- **Products**: Create, Read, Update, Delete (Admin only)
- **Users**: Create, Read, Update, Delete (Admin only)
- **Orders**: Create, Read, Update, Delete (Admin can manage all, Users can manage their own)

### 🗄️ Database Schema
- **products** table with id, name, category, price, stock
- **users** table with id, username, password, role (ADMIN/USER)
- **orders** table with id, customer_id
- **order_items** table for order details

## Prerequisites

1. **Java 17** or higher
2. **MySQL 8.0** or higher
3. **Maven 3.6** or higher

## Setup Instructions

### 1. Database Setup
```sql
-- Create database
CREATE DATABASE ecommerce;

-- The application will automatically create tables using schema.sql
```

### 2. Configuration
Update `src/main/resources/application.properties` with your MySQL credentials:
```properties
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### 3. Run the Application
```bash
# Navigate to project directory
cd ecommerce_springboot

# Run the application
mvn spring-boot:run
```

## Usage

The application starts with a login screen where you can:

### 🔐 Login Options
- **1. Login** - Enter username and password
- **2. Register** - Create new user account (automatically assigned USER role)
- **3. Exit** - Close the application

### 👨‍💼 Admin Interface (After Login as Admin)
**Product Management:**
- **1. Add Product** - Create new products
- **2. View All Products** - List all products
- **3. Update Product** - Modify existing products
- **4. Delete Product** - Remove products

**User Management:**
- **5. View All Users** - List all users with roles
- **6. Update User** - Modify user details and roles
- **7. Delete User** - Remove users

**Order Management:**
- **8. View All Orders** - List all orders
- **9. Update Order** - Modify any order
- **10. Delete Order** - Remove any order

**Account:**
- **11. Logout** - Return to login screen
- **12. Exit** - Close application

### 👤 User Interface (After Login as User)
**Shopping:**
- **1. View All Products** - Browse available products
- **2. Place Order** - Create new order (automatically uses your user ID)
- **3. View My Orders** - See only your orders

**Account:**
- **4. Update Profile** - Modify your username/password
- **5. Logout** - Return to login screen
- **6. Exit** - Close application

## Sample Data

The application comes with sample data:
- **Products**: Laptop, Mouse, Keyboard, Book, Pen
- **Users**: 
  - `admin` / `admin123` (ADMIN role)
  - `user1` / `password123` (USER role)
  - `user2` / `password456` (USER role)

## Default Login Credentials

**Admin Access:**
- Username: `admin`
- Password: `admin123`

**User Access:**
- Username: `user1` or `user2`
- Password: `password123` or `password456`

## Technical Details

- **Framework**: Spring Boot 3.5.5
- **Database**: MySQL with JDBC
- **Architecture**: DAO pattern with Service layer
- **Dependency Injection**: Spring IoC container

## Troubleshooting

### Common Issues

1. **Database Connection Error**
   - Ensure MySQL is running
   - Check credentials in application.properties
   - Verify database exists

2. **Table Not Found**
   - Check if schema.sql is in src/main/resources/
   - Verify spring.sql.init.mode=always in application.properties

3. **Port Already in Use**
   - Change server port in application.properties
   - Kill existing Java processes

## CRUD Operations Verification

To test CRUD operations:

1. **Create**: Add new products/users/orders
2. **Read**: View all items and individual items by ID
3. **Update**: Modify existing items
4. **Delete**: Remove items with confirmation

All operations include proper error handling and user feedback.
