CREATE TABLE users(
    id SERIAL PRIMARY KEY,
    username VARCHAR(24),
    password VARCHAR(32),
    email VARCHAR(42),
    role VARCHAR(12),
    activation_token VARCHAR(50),
    reset_token VARCHAR(50),
    mfa BOOLEAN,
    mailing BOOLEAN,
    secret VARCHAR(150),
    create_at TIMESTAMP,
    update_at TIMESTAMP
)