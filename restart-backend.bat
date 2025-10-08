@echo off
echo Restarting E-Commerce Backend Services...
echo.

echo Stopping existing services...
taskkill /f /im java.exe 2>nul

echo Waiting 3 seconds...
timeout /t 3 /nobreak >nul

echo Starting services with updated security...
echo.

echo Starting Product Service on port 8081...
start "Product Service" cmd /k "cd ecommerce-microservices\product-service && mvn spring-boot:run"

echo Starting User Service on port 8082...
start "User Service" cmd /k "cd ecommerce-microservices\user-service && mvn spring-boot:run"

echo Starting Order Service on port 8083...
start "Order Service" cmd /k "cd ecommerce-microservices\order-service && mvn spring-boot:run"

echo.
echo Services restarted with updated security settings!
echo No more authentication popups should appear.
echo.
echo Product Service: http://localhost:8081
echo User Service: http://localhost:8082
echo Order Service: http://localhost:8083
echo.
echo Press any key to continue...
pause
