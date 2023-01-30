CREATE TABLE product_category(
    product_id INT REFERENCES product(id),
    category_id INT REFERENCES category(id)
)