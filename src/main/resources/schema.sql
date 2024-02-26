-- Создание таблицы для пицц
CREATE TABLE IF NOT EXISTS pizzas
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(255)   NOT NULL,
    description TEXT,
    price       DECIMAL(10, 2) NOT NULL,
    is_active   BOOLEAN DEFAULT TRUE
);

-- Создание таблицы для ингредиентов
CREATE TABLE IF NOT EXISTS ingredients
(
    id        SERIAL PRIMARY KEY,
    name      VARCHAR(255)   NOT NULL,
    price     DECIMAL(10, 2) NOT NULL,
    is_active BOOLEAN DEFAULT TRUE
);

-- Создание таблицы для связи между пиццами и ингредиентами
CREATE TABLE IF NOT EXISTS pizza_ingredients
(
    pizza_id      INT NOT NULL,
    ingredient_id INT NOT NULL,
    PRIMARY KEY (pizza_id, ingredient_id),
    FOREIGN KEY (pizza_id) REFERENCES pizzas (id) ON DELETE CASCADE,
    FOREIGN KEY (ingredient_id) REFERENCES ingredients (id) ON DELETE CASCADE
);

-- Создание таблицы для пользователей
CREATE TABLE IF NOT EXISTS users
(
    id         SERIAL PRIMARY KEY,
    username   VARCHAR(255) UNIQUE NOT NULL,
    password   VARCHAR(255)        NOT NULL,
    email      VARCHAR(255) UNIQUE NOT NULL,
    is_active  BOOLEAN                  DEFAULT TRUE,
    created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

-- Создание таблицы для заказов
CREATE TABLE IF NOT EXISTS orders
(
    id          SERIAL PRIMARY KEY,
    user_id     INT            NOT NULL,
    total_price DECIMAL(10, 2) NOT NULL,
    created_at  TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    status      VARCHAR(50)              DEFAULT 'PENDING',
    FOREIGN KEY (user_id) REFERENCES users (id)
);

-- Создание таблицы для связи между заказами и пиццами
CREATE TABLE IF NOT EXISTS order_items
(
    order_id INT NOT NULL,
    pizza_id INT NOT NULL,
    quantity INT NOT NULL,
    PRIMARY KEY (order_id, pizza_id),
    FOREIGN KEY (order_id) REFERENCES orders (id) ON DELETE CASCADE,
    FOREIGN KEY (pizza_id) REFERENCES pizzas (id) ON DELETE CASCADE
);