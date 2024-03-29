CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    email VARCHAR(100),
    phone_number VARCHAR(15),
    username VARCHAR(30),
    password VARCHAR(60),
    enabled BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE IF NOT EXISTS concerts (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT,
    artist VARCHAR(100),
    venue VARCHAR(100),
    setlist TEXT,
    FOREIGN KEY (user_id) REFERENCES users(id)
);
