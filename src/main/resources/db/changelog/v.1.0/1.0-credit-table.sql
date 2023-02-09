CREATE TABLE credit_card(
    id SERIAL PRIMARY KEY,
    number TEXT,
    full_name TEXT,
    validity TIMESTAMP,
    cvv TEXT,
    user_id INT REFERENCES users(id)
)