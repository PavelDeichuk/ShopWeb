CREATE TABLE product(
    id SERIAL PRIMARY KEY,
    name VARCHAR(52),
    description TEXT,
    price INT,
    create_at TIMESTAMP,
    update_at TIMESTAMP,
    image_id INT REFERENCES image(id)
)