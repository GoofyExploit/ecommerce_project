@echo off
echo Starting E-Commerce Backend Services...
echo.
echo These services will run WITHOUT console prompts
echo Use the web frontend at http://localhost:3000
echo.

echo Starting Product Service on port 8081...
start "Product Service" cmd /k "cd ecommerce-microservices\product-service && mvn spring-boot:run"

echo Starting User Service on port 8082...
start "User Service" cmd /k "cd ecommerce-microservices\user-service && mvn spring-boot:run"

echo Starting Order Service on port 8083...
start "Order Service" cmd /k "cd ecommerce-microservices\order-service && mvn spring-boot:run"

echo.
echo All backend services are starting...
echo Product Service: http://localhost:8081
echo User Service: http://localhost:8082
echo Order Service: http://localhost:8083
echo.
echo No console prompts will appear - use the web frontend!
echo.
echo Press any key to continue...
pause
