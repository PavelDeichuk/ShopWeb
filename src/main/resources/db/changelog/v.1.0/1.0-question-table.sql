CREATE TABLE question(
    id SERIAL PRIMARY KEY,
    name VARCHAR(200),
    description TEXT,
    create_at TIMESTAMP,
    update_at TIMESTAMP,
    user_id INT REFERENCES users(id),
    product_id INT REFERENCES product(id),
    image_id INT REFERENCES image(id)
)