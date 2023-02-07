CREATE TABLE discount(
    id SERIAL PRIMARY KEY,
    price INT,
    start_at TIMESTAMP,
    end_at TIMESTAMP
)