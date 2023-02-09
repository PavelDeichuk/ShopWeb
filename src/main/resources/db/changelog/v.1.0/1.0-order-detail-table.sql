CREATE TABLE order_detail(
    id SERIAL PRIMARY KEY,
    price INT,
    order_id INT REFERENCES orders(id),
    product_id INT REFERENCES product(id)
)