CREATE TABLE image(
    id SERIAL PRIMARY KEY,
    name TEXT,
    image bytea,
    size INT
)