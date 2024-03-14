-- Добавление данных в таблицу pizzas
INSERT INTO pizzas (name, description, price)
VALUES ('Margherita', 'Simple pizza with tomatoes, mozzarella cheese, and fresh basil', 7.50);
INSERT INTO pizzas (name, description, price)
VALUES ('Pepperoni', 'Pizza with pepperoni, mozzarella cheese, and tomato sauce', 8.50);
INSERT INTO pizzas (name, description, price)
VALUES ('Hawaiian', 'Pizza with ham, pineapple, and mozzarella cheese', 9.00);

-- Добавление данных в таблицу ingredients
INSERT INTO ingredients (name, price)
VALUES ('Tomato Sauce', 0.50);
INSERT INTO ingredients (name, price)
VALUES ('Mozzarella Cheese', 0.75);
INSERT INTO ingredients (name, price)
VALUES ('Pepperoni', 1.00);
INSERT INTO ingredients (name, price)
VALUES ('Pineapple', 1.00);
INSERT INTO ingredients (name, price)
VALUES ('Ham', 1.50);

-- Добавление тестового пользователя
INSERT INTO users (username, password, email)
VALUES ('testuser', 'testpass', 'test@example.com');