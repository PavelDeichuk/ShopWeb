CREATE TABLE users_detail(
    id SERIAL PRIMARY KEY,
    firstname VARCHAR(24),
    surname VARCHAR(28),
    age INT,
    birthday TIMESTAMP,
    description TEXT,
    users_id INT UNIQUE REFERENCES users(id)
)