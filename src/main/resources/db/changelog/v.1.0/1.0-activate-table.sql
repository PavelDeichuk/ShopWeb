CREATE TABLE activate(
    id SERIAL PRIMARY KEY,
    activates INT,
    user_id INT REFERENCES users(id),
    promo_id INT REFERENCES promo(id)
)