CREATE TABLE wish(
    id SERIAL PRIMARY KEY,
    session TEXT,
    user_id INT UNIQUE REFERENCES users(id)
)