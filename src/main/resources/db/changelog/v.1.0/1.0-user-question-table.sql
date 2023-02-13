CREATE TABLE user_question(
    user_id INT REFERENCES users(id),
    question_id INT REFERENCES question(id)
)