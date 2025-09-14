USE ecommerce;

ALTER TABLE users 
ADD COLUMN IF NOT EXISTS role ENUM('ADMIN', 'USER') NOT NULL DEFAULT 'USER';

UPDATE users SET role = 'ADMIN' WHERE username = 'admin';

UPDATE users SET role = 'USER' WHERE role IS NULL OR role = '';

SELECT id, username, role FROM users;
