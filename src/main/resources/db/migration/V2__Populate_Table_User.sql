INSERT INTO users (uuid, name, role, phone, is_verified)
VALUES (
           UUID(),
           'Admin User',
           0,                 -- 0 para ADMIN
           '+5511888888888',
           TRUE
       );
