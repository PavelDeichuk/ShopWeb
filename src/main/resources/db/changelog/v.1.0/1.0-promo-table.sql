CREATE TABLE promo(
    id SERIAL PRIMARY KEY,
    name VARCHAR(32),
    price INT,
    start_at TIMESTAMP,
    end_at TIMESTAMP,
    activates INT
)