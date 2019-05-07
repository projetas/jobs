INSERT INTO roles(name) VALUES('ROLE_ADMIN');
INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_PM');

INSERT INTO users(name, username, email, password) VALUES('Administrador', 'admin', 'admin@projetas.com', '$2a$10$1Rqm5oCoEwFlH4M.arpJCeYO3vjfhJVorVUDd1lJBnQ8xCqDWBNB.');
INSERT INTO users(name, username, email, password) VALUES('Usuário', 'user', 'user@projetas.com', '$2a$10$1Rqm5oCoEwFlH4M.arpJCeYO3vjfhJVorVUDd1lJBnQ8xCqDWBNB.');

INSERT INTO user_roles(user_id, role_id) VALUES(1, 1);
INSERT INTO user_roles(user_id, role_id) VALUES(2, 2);

INSERT INTO vehicle(brand, model, color, year, price, description, is_new, save_date) VALUES('Chevrolet', 'Cruze LTZ', 'White', 2019, 62000.00, 'O melhor motor da categoria.', 1, CURRENT_TIMESTAMP());
INSERT INTO vehicle(brand, model, color, year, price, description, is_new, save_date) VALUES('Chevrolet', 'Cobalt LTZ', 'White', 2019, 78000.00, 'Maior espaço interno.', 1, CURRENT_TIMESTAMP());
INSERT INTO vehicle(brand, model, color, year, price, description, is_new, save_date) VALUES('Volkswagen', 'Fusca', 'Preto', 1985, 3500.00, 'Em bom estado de conservação.', 0, CURRENT_TIMESTAMP());
INSERT INTO vehicle(brand, model, color, year, price, description, is_new, save_date) VALUES('Volkswagen', 'Kombi', 'Amarelo', 1977, 3800.00, 'Dá para o gasto ;)', 0, CURRENT_TIMESTAMP());
