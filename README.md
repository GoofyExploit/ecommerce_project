# 🛒 E-Commerce Spring Boot Application

A complete e-commerce management system with role-based access control, built with Spring Boot and MySQL.

## 🌟 Features

### 🔐 Authentication & Authorization
- **Login System**: Secure username/password authentication
- **Role-Based Access**: Separate interfaces for Admin and User roles
- **User Registration**: Create new accounts with role selection
- **Session Management**: Persistent login sessions with logout functionality

### 👨‍💼 Admin Features
- **Product Management**: Create, Read, Update, Delete products
- **User Management**: View, Update, Delete users and their roles
- **Order Management**: View, Update, Delete all orders
- **Full CRUD Operations**: Complete control over all system entities

### 👤 User Features
- **Product Browsing**: View all available products
- **Order Placement**: Create orders with multiple items
- **Order History**: View personal order history
- **Profile Management**: Update personal account details

### 🗄️ Database Features
- **MySQL Integration**: Robust database connectivity
- **Auto Schema Creation**: Automatic table creation on startup
- **Sample Data**: Pre-loaded with test products and users
- **Data Validation**: Input validation and error handling

## 🚀 Quick Start

### Prerequisites
- **Java 17** or higher
- **MySQL 8.0** or higher
- **Maven 3.6** or higher

### Installation

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd ecommerce_springboot
   ```

2. **Configure Database**
   ```sql
   CREATE DATABASE ecommerce;
   ```

3. **Update Configuration**
   Edit `src/main/resources/application.properties`:
   ```properties
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

4. **Run the Application**
   ```bash
   mvn spring-boot:run
   ```

## 🎯 Usage Guide

### Login Screen
```
=== LOGIN ===
1. Login
2. Register
3. Test Database Connection
4. Exit
```

### Default Credentials
**Admin Access:**
- Username: `admin`
- Password: `admin123`

**User Access:**
- Username: `user1` / `user2`
- Password: `password123` / `password456`

### Admin Dashboard
```
=== ADMIN DASHBOARD ===
=== PRODUCT MANAGEMENT ===
1. Add Product
2. View All Products
3. Update Product
4. Delete Product
=== USER MANAGEMENT ===
5. View All Users
6. Update User
7. Delete User
=== ORDER MANAGEMENT ===
8. View All Orders
9. Update Order
10. Delete Order
=== ACCOUNT ===
11. Logout
12. Exit
```

### User Dashboard
```
=== USER DASHBOARD ===
=== SHOPPING ===
1. View All Products
2. Place Order
3. View My Orders
=== ACCOUNT ===
4. Update Profile
5. Logout
6. Exit
```

## 🏗️ Project Structure

```
ecommerce_springboot/
├── src/main/java/com/ecommerce/ecommerce_springboot/
│   ├── EcommerceApp.java                 # Main application class
│   ├── app/
│   │   ├── ConsoleApp.java              # Main console interface
│   │   ├── DatabaseTest.java            # Database testing utility
│   │   └── DatabaseFixer.java           # Database schema fixer
│   ├── controller/
│   │   └── ConsoleController.java       # Legacy controller
│   ├── dao/
│   │   ├── ProductDAO.java              # Product data access
│   │   ├── UserDAO.java                 # User data access
│   │   └── OrderDAO.java                # Order data access
│   ├── model/
│   │   ├── Product.java                 # Product entity
│   │   ├── User.java                    # User entity
│   │   ├── Order.java                   # Order entity
│   │   └── OrderItem.java               # Order item entity
│   └── service/
│       ├── ProductService.java          # Product business logic
│       ├── UserService.java             # User business logic
│       └── OrderService.java            # Order business logic
├── src/main/resources/
│   ├── application.properties           # Configuration
│   ├── schema.sql                       # Database schema
│   └── migration.sql                    # Database migration
└── pom.xml                              # Maven dependencies
```

## 🗄️ Database Schema

### Tables
- **products**: Product catalog with id, name, category, price, stock
- **users**: User accounts with id, username, password, role
- **orders**: Order records with id, customer_id
- **order_items**: Order details with id, order_id, product_id, quantity, price

### Sample Data
- **5 Products**: Laptop, Mouse, Keyboard, Book, Pen
- **3 Users**: admin (ADMIN), user1 (USER), user2 (USER)

## 🔧 Configuration

### Application Properties
```properties
# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce
spring.datasource.username=root
spring.datasource.password=your_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# Database Initialization
spring.sql.init.mode=always
spring.sql.init.schema-locations=classpath:schema.sql
spring.sql.init.continue-on-error=true

# JPA Settings
spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=true
```

### Maven Dependencies
- Spring Boot Starter
- Spring Boot JDBC
- MySQL Connector
- Spring Security Crypto
- Lombok (Optional)

## 🛠️ Development

### Adding New Features
1. **Models**: Add new entities in `model/` package
2. **DAOs**: Create data access objects in `dao/` package
3. **Services**: Implement business logic in `service/` package
4. **Controllers**: Add new controllers in `controller/` package

### Database Changes
1. Update `schema.sql` for new tables/columns
2. Create migration scripts in `migration.sql`
3. Update corresponding DAO classes
4. Test with DatabaseTest utility

## 🧪 Testing

### Database Connection Test
- Use option 3 in login menu
- Verifies database connectivity
- Shows table status and record counts

### Registration Test
- Create new users with different roles
- Test input validation
- Verify role assignment

### CRUD Operations Test
- Test all Create, Read, Update, Delete operations
- Verify admin vs user access restrictions
- Test error handling

## 🐛 Troubleshooting

### Common Issues

**Database Connection Error**
```
Solution: Check MySQL service, verify credentials in application.properties
```

**Table Not Found Error**
```
Solution: Ensure schema.sql is in src/main/resources/
```

**Registration Fails**
```
Solution: Use Database Test option to verify database schema
```

**Login Issues**
```
Solution: Verify username/password, check user exists in database
```

### Debug Tools
- **Database Test**: Option 3 in login menu
- **Error Logs**: Check console output for detailed error messages
- **Stack Traces**: Enabled for debugging registration issues

## 📋 API Reference

### User Management
- `addUser(User user)` - Create new user
- `getUserById(int id)` - Get user by ID
- `getUserByUsername(String username)` - Get user by username
- `updateUser(User user)` - Update user details
- `deleteUser(int id)` - Delete user

### Product Management
- `addProduct(Product product)` - Create new product
- `getAllProducts()` - Get all products
- `getProductById(int id)` - Get product by ID
- `updateProduct(Product product)` - Update product
- `deleteProduct(int id)` - Delete product

### Order Management
- `addOrder(Order order)` - Create new order
- `getAllOrders()` - Get all orders
- `getOrderById(int id)` - Get order by ID
- `updateOrder(Order order)` - Update order
- `deleteOrder(int id)` - Delete order

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Test thoroughly
5. Submit a pull request

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 👥 Authors

- **Your Name** - *Initial work* - [YourGitHub](https://github.com/yourusername)

## 🙏 Acknowledgments

- Spring Boot team for the excellent framework
- MySQL team for the robust database
- Open source community for inspiration

---

## 📞 Support

If you encounter any issues or have questions:
1. Check the troubleshooting section
2. Use the built-in database test tools
3. Review the error logs
4. Create an issue in the repository

**Happy Coding! 🚀**