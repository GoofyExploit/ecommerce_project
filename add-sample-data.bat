@echo off
echo Adding sample data to the e-commerce services...
echo.

echo Adding sample products...
curl -X POST http://localhost:8081/products -H "Content-Type: application/json" -d "{\"name\":\"Laptop\",\"price\":999.99}"
curl -X POST http://localhost:8081/products -H "Content-Type: application/json" -d "{\"name\":\"Mouse\",\"price\":29.99}"
curl -X POST http://localhost:8081/products -H "Content-Type: application/json" -d "{\"name\":\"Keyboard\",\"price\":79.99}"
curl -X POST http://localhost:8081/products -H "Content-Type: application/json" -d "{\"name\":\"Monitor\",\"price\":299.99}"
curl -X POST http://localhost:8081/products -H "Content-Type: application/json" -d "{\"name\":\"Headphones\",\"price\":149.99}"

echo.
echo Adding sample users...
curl -X POST http://localhost:8082/users -H "Content-Type: application/json" -d "{\"username\":\"admin\",\"password\":\"admin123\",\"role\":\"ADMIN\"}"
curl -X POST http://localhost:8082/users -H "Content-Type: application/json" -d "{\"username\":\"user1\",\"password\":\"password123\",\"role\":\"USER\"}"
curl -X POST http://localhost:8082/users -H "Content-Type: application/json" -d "{\"username\":\"user2\",\"password\":\"password456\",\"role\":\"USER\"}"

echo.
echo Sample data added successfully!
echo You can now use these credentials to login:
echo Admin: admin / admin123
echo User: user1 / password123
echo User: user2 / password456
echo.
pause
