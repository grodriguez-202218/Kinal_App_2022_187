INSERT INTO usuarios (username, password, email, rol, estado)
SELECT 'admin',
       '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVKIUi',
       'admin@kinalapp.com',
       'ADMIN',
       1
    WHERE NOT EXISTS (
    SELECT 1 FROM usuarios WHERE username = 'admin'
);