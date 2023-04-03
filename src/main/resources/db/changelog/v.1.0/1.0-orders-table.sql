CREATE TABLE orders (
    id SERIAL PRIMARY KEY,
    name VARCHAR(32),
    surname VARCHAR(32),
    phone VARCHAR(32),
    address VARCHAR(64),
    order_status VARCHAR(255),
    user_id INT REFERENCES users(id)
)