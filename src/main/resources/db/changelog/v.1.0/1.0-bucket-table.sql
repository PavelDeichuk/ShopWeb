CREATE TABLE bucket(
    id SERIAL PRIMARY KEY,
    session TEXT,
    user_id INT REFERENCES users(id)
)