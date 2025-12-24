DELETE FROM users;
INSERT INTO users (user_id, password, role) VALUES 
('user01', '$2a$10$9rTe0QwZGPRwinSfgy29.uAPOBDAZkxl14XytKXO.iXdO/RWw/yu.', 'ROLE_USER'),
('user02', '$2a$10$9rTe0QwZGPRwinSfgy29.uAPOBDAZkxl14XytKXO.iXdO/RWw/yu.', 'ROLE_USER'),
('kaito', '$2a$10$j.TrzVKf6neZ2Reul4arBec8RWGtaxVUurKp0L1umh40iW9vjVZLa','ROLE_ADMIN');